/**
 * LICENSE
 *
 * This source file is subject to the MIT license that is bundled
 * with this package in the file MIT.txt.
 * It is also available through the world-wide-web at this URL:
 * http://www.opensource.org/licenses/mit-license.html
 */

package com.pactera.esc.core.tree;

import com.pactera.esc.core.annotations.LeftColumn;
import com.pactera.esc.core.annotations.LevelColumn;
import com.pactera.esc.core.annotations.RightColumn;
import com.pactera.esc.core.annotations.RootColumn;
import net.jcip.annotations.NotThreadSafe;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** The default implementation of a JPA {@link NestedSetManager}. */
@NotThreadSafe
@Component
public class JpaNestedSetManager implements NestedSetManager {

    @PersistenceContext
    private EntityManager em;
    private final Map<Class<?>, Configuration> configs= new HashMap<>();

  /*  @Inject
    public JpaNestedSetManager(EntityManager em) {
        this.em = em;
        //this.nodes = new HashMap<Key, Node<?>>();
        //this.configs = new HashMap<Class<?>, Configuration>();
    }*/

    /**
     * {@inheritDoc}
     */
    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends NodeInfo> List<Node<T>> listNodes(Class<T> clazz) {
        return listNodes(clazz, 0);
    }

    // TODO: Claude; 6.18; redundancy
    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends NodeInfo> List<Node<T>> listNodes(Class<T> clazz, int rootId) {
        Configuration config = getConfig(clazz);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> queryRoot = cq.from(clazz);
        cq.where(cb.ge(queryRoot.<Number>get(config.getLeftFieldName()), 1));
        cq.orderBy(cb.asc(queryRoot.get(config.getLeftFieldName())));
        applyRootId(clazz, cq, rootId);

        List<Node<T>> nodes = new ArrayList<>();
        for (T n : em.createQuery(cq).getResultList()) {
            nodes.add(getNode(n));
        }

        return nodes;
    }

    @Override
    public <T extends NodeInfo> List<Node<T>> listTrees(Class<T> clazz) {
        Configuration config = getConfig(clazz);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> queryRoot = cq.from(clazz);
        cq.where(cb.ge(queryRoot.<Number>get(config.getLeftFieldName()), 1));
        cq.orderBy(cb.asc(queryRoot.get(config.getRootIdFieldName())),
                cb.asc(queryRoot.get(config.getLeftFieldName())));

        List<Node<T>> nodes = new ArrayList<>();
        for (T n : em.createQuery(cq).getResultList()) {
            nodes.add(getNode(n));
        }

        return nodes;
    }

    @Override
    public <T extends NodeInfo> List<Node<T>> listRoots(Class<T> clazz) {
        Configuration config = getConfig(clazz);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> queryRoot = cq.from(clazz);
        cq.where(cb.equal(queryRoot.<Number>get(config.getLevelFieldName()), 0));
        cq.orderBy(cb.desc(queryRoot.get(config.getRootIdFieldName())));

        List<Node<T>> nodes = new ArrayList<>();
        for (T n : em.createQuery(cq).getResultList()) {
            nodes.add(getNode(n));
        }

        return nodes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends NodeInfo> Node<T> createRoot(T root) {
        if (root.getLeftValue() < root.getRightValue()) {
            throw new IllegalArgumentException("The node already has a position in a tree.");
        }

        Configuration config = getConfig(root.getClass());

        int maximumRight;
        if (config.hasManyRoots()) {
            maximumRight = 0;
        } else {
            maximumRight = getMaximumRight(root.getClass());
        }
        root.setLeftValue(maximumRight + 1);
        root.setRightValue(maximumRight + 2);
        root.setLevel(0);
        em.persist(root);

        return getNode(root);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends NodeInfo> Node<T> getNode(T nodeInfo) {
        Node<T> node = new JpaNode<>(nodeInfo, this);
        if (!node.isValid()) {
            throw new IllegalArgumentException("The given NodeInfo instance has no position " +
                    "in a tree and is thus not yet a node.");
        }
        return node;
    }

    Configuration getConfig(Class<?> clazz) {
        if (this.configs.containsKey(clazz)) {
            return this.configs.get(clazz);
        }
        Configuration config = new Configuration();

        Entity entity = clazz.getAnnotation(Entity.class);
        String name = entity.name();
        config.setEntityName(name.length() > 0 ? name : clazz.getSimpleName());

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(LeftColumn.class) != null) {
                config.setLeftFieldName(field.getName());
            }
            else if (field.getAnnotation(RightColumn.class) != null) {
                config.setRightFieldName(field.getName());
            }
            else if (field.getAnnotation(LevelColumn.class) != null) {
                config.setLevelFieldName(field.getName());
            }
            else if (field.getAnnotation(RootColumn.class) != null) {
                config.setRootIdFieldName(field.getName());
            }
        }

        this.configs.put(clazz, config);

        return this.configs.get(clazz);
    }

    private int getMaximumRight(Class<? extends NodeInfo> clazz) {
    	Configuration config = getConfig(clazz);
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<? extends NodeInfo> cq = cb.createQuery(clazz);
        Root<? extends NodeInfo> queryRoot = cq.from(clazz);
        cq.orderBy(cb.desc(queryRoot.get(config.getRightFieldName())));
        List<? extends NodeInfo>highestRows = em.createQuery(cq).setMaxResults(1).getResultList();
        if (highestRows.isEmpty()) {
        	return 0;
        } else {
        	return highestRows.get(0).getRightValue();
        }
    }

    void applyRootId(Class<?> clazz, CriteriaQuery<?> cq, int rootId) {
        Configuration config = getConfig(clazz);
        if (config.getRootIdFieldName() == null) {
            return;
        }
        Root<?> root = cq.getRoots().iterator().next();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Predicate p = cq.getRestriction();
        cq.where(cb.and(p, cb.equal(root.get(config.getRootIdFieldName()), rootId)));
    }
}
