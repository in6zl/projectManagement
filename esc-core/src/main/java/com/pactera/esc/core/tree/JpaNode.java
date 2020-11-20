/**
 * LICENSE
 *
 * This source file is subject to the MIT license that is bundled
 * with this package in the file MIT.txt.
 * It is also available through the world-wide-web at this URL:
 * http://www.opensource.org/licenses/mit-license.html
 */

package com.pactera.esc.core.tree;

import net.jcip.annotations.NotThreadSafe;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * A decorator for a {@link NodeInfo} implementation that enriches it with the full API
 * of a node in a nested set tree.
 *
 * @param <T extends NodeInfo> The wrapped entity type.
 */
@NotThreadSafe
class JpaNode<T extends NodeInfo> implements Node<T> {
    private static final int PREV_SIBLING = 1;
    private static final int FIRST_CHILD = 2;
    private static final int NEXT_SIBLING = 3;
    private static final int LAST_CHILD = 4;

    private final JpaNestedSetManager nsm;
    private final T node;
    private final Class<T> type;

    private CriteriaQuery<T> baseQuery;
    private Root<T> queryRoot;

    @SuppressWarnings("unchecked")
    public JpaNode(T node, JpaNestedSetManager nsm) {
        this.node = node;
        this.nsm = nsm;
        this.type = (Class<T>) node.getClass();
    }

    @Override public String getId() {
        return this.node.getId();
    }

    @Override public int getLeftValue() {
        return this.node.getLeftValue();
    }

    @Override public int getRightValue() {
        return this.node.getRightValue();
    }

    @Override public int getLevel() {
        return this.node.getLevel();
    }

    @Override public int getRootValue() {
        return this.node.getRootValue();
    }

    @Override public void setRootValue(int value) {
        this.node.setRootValue(value);
    }

    @Override public void setLeftValue(int value) {
        this.node.setLeftValue(value);
    }

    @Override public void setRightValue(int value) {
        this.node.setRightValue(value);
    }

    @Override public void setLevel(int level) {
        this.node.setLevel(level);
    }

    @Override
    public String toString() {
        return "[Left: " + node.getLeftValue() +
                ", Right: " + node.getRightValue() +
                ", Level: " + node.getLevel() +
                ", NodeInfo: " + node.toString() +
                "]";
    }

    @Override
    public boolean hasChildren() {
        return (getRightValue() - getLeftValue()) > 1;
    }

    @Override
    public boolean hasParent() {
        return !isRoot();
    }

    @Override
    public boolean isValid() {
        return isValidNode(this);
    }

    private boolean isValidNode(NodeInfo node) {
        return node != null && node.getRightValue() > node.getLeftValue();
    }

    private CriteriaQuery<T> getBaseQuery() {
        if (this.baseQuery == null) {
            this.baseQuery = nsm.getEntityManager().getCriteriaBuilder().createQuery(type);
            this.queryRoot = this.baseQuery.from(type);
        }
        return this.baseQuery;
    }

    public int getNumberOfChildren() {
        return getChildren().size();
    }

    public int getNumberOfDescendants() {
        return (this.getRightValue() - this.getLeftValue() - 1) / 2;
    }

    @Override
    public boolean isRoot() {
        return getLeftValue() == 1;
    }

    @Override
    public List<Node<T>> getChildren() {
        return getDescendants(1);
    }


    @Override
    public Node<T> getParent() {
        if (isRoot()) {
            return null;
        }

        CriteriaBuilder cb = nsm.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = getBaseQuery();
        cq.where(cb.lt(
                    queryRoot.<Number>get(nsm.getConfig(this.type).getLeftFieldName()),
                    getLeftValue()
                    ),
                cb.gt(
                    queryRoot.<Number>get(nsm.getConfig(this.type).getRightFieldName()),
                    getRightValue()
                    ));
        cq.orderBy(cb.asc(queryRoot.get(nsm.getConfig(this.type).getRightFieldName())));
        nsm.applyRootId(this.type, cq, getRootValue());

        List<T> result = nsm.getEntityManager().createQuery(cq).getResultList();

        return nsm.getNode(result.get(0));
    }

    @Override public List<Node<T>> getDescendants() {
        return getDescendants(0);
    }

    @Override public List<Node<T>> getDescendants(int depth) {
        CriteriaBuilder cb = nsm.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = getBaseQuery();
        Predicate wherePredicate = cb.and(
                cb.gt(
                    queryRoot.<Number>get(nsm.getConfig(this.type).getLeftFieldName()),
                    getLeftValue()
                    ),
                cb.lt(
                    queryRoot.<Number>get(nsm.getConfig(this.type).getRightFieldName()),
                    getRightValue()
                    ));

        if (depth > 0) {
            wherePredicate = cb.and(
                    wherePredicate,
                    cb.le(
                        queryRoot.<Number>get(nsm.getConfig(this.type).getLevelFieldName()),
                        getLevel() + depth)
                        );
        }
        cq.where(wherePredicate);
        cq.orderBy(cb.asc(queryRoot.get(nsm.getConfig(this.type).getLeftFieldName())));

        nsm.applyRootId(this.type, cq, getRootValue());

        List<Node<T>> nodes = new ArrayList<Node<T>>();
        for (T n : nsm.getEntityManager().createQuery(cq).getResultList()) {
            nodes.add(nsm.getNode(n));
        }

        return nodes;
    }

    @Override
    public Node<T> addChild(T child) {
        if (child == this.node) {
            throw new IllegalArgumentException("Cannot add node as child of itself.");
        }

        int newLeft = getRightValue();
        int newRight = getRightValue() + 1;
        int newRoot = getRootValue();

        shiftRLValues(newLeft, 0, 2, newRoot);
        child.setLevel(getLevel() + 1);
        child.setLeftValue(newLeft);
        child.setRightValue(newRight);
        child.setRootValue(newRoot);
        nsm.getEntityManager().persist(child);
        return this.nsm.getNode(child);
    }

    private void insertAsPrevSiblingOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot add node as child of itself.");
        }

        int newLeft = dest.getLeftValue();
        int newRight = dest.getLeftValue() + 1;
        int newRoot = dest.getRootValue();

        shiftRLValues(newLeft, 0, 2, newRoot);
        setLevel(dest.getLevel());
        setLeftValue(newLeft);
        setRightValue(newRight);
        setRootValue(newRoot);
        nsm.getEntityManager().persist(this.node);
    }

    private void insertAsNextSiblingOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot add node as child of itself.");
        }

        int newLeft = dest.getRightValue() + 1;
        int newRight = dest.getRightValue() + 2;
        int newRoot = dest.getRootValue();

        shiftRLValues(newLeft, 0, 2, newRoot);
        setLevel(dest.getLevel());
        setLeftValue(newLeft);
        setRightValue(newRight);
        setRootValue(newRoot);
        nsm.getEntityManager().persist(this.node);
    }

    private void insertAsLastChildOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot add node as child of itself.");
        }

        int newLeft = dest.getRightValue();
        int newRight = dest.getRightValue() + 1;
        int newRoot = dest.getRootValue();

        shiftRLValues(newLeft, 0, 2, newRoot);
        setLevel(dest.getLevel() + 1);
        setLeftValue(newLeft);
        setRightValue(newRight);
        setRootValue(newRoot);
        nsm.getEntityManager().persist(this.node);
    }

    private void insertAsFirstChildOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot add node as child of itself.");
        }

        int newLeft = dest.getLeftValue() + 1;
        int newRight = dest.getLeftValue() + 2;
        int newRoot = dest.getRootValue();

        shiftRLValues(newLeft, 0, 2, newRoot);
        setLevel(dest.getLevel());
        setLeftValue(newLeft);
        setRightValue(newRight);
        setRootValue(newRoot);
        nsm.getEntityManager().persist(this.node);
    }

    @Override
    public void delete() {
        int oldRoot = getRootValue();
        Configuration cfg = nsm.getConfig(this.type);
        String rootIdFieldName = cfg.getRootIdFieldName();
        String leftFieldName = cfg.getLeftFieldName();
        String rightFieldName = cfg.getRightFieldName();
        String entityName =  cfg.getEntityName();

        StringBuilder sb = new StringBuilder();
        sb.append("delete from " )
                .append(entityName).append(" n")
                .append(" where n.").append(leftFieldName).append(">= :a")
                .append(" and n.").append(rightFieldName).append("<= :b");

        if (rootIdFieldName != null) {
            sb.append(" and n.").append(rootIdFieldName).append("= :c");
        }

        Query q = nsm.getEntityManager().createQuery(sb.toString());
        q.setParameter("a", getLeftValue());
        q.setParameter("b", getRightValue());
        if (rootIdFieldName != null) {
            q.setParameter("c", oldRoot);
        }
        q.executeUpdate();

        // Close gap in tree
        int first = getRightValue() + 1;
        int delta = getLeftValue() - getRightValue() - 1;
        shiftRLValues(first, 0, delta, oldRoot);
    }

    /**
     * Adds 'delta' to all left and right values that are >= 'first' and
     * <= 'last'. 'delta' can also be negative. If 'last' is 0 it is skipped and there is
     * no upper bound.
     *
     * @param first The first left/right mask (inclusive) of the nodes to shift.
     * @param last The last left/right mask (inclusive) of the nodes to shift.
     * @param delta The offset by which to shift the left/right values (can be negative).
     * @param rootId The root/tree ID of the nodes to shift.
     */
    private synchronized void shiftRLValues(int first, int last, int delta, int rootId) {
    	Configuration cfg = nsm.getConfig(this.type);
        String rootIdFieldName = cfg.getRootIdFieldName();
        String leftFieldName = cfg.getLeftFieldName();
        String rightFieldName = cfg.getRightFieldName();
        String entityName = cfg.getEntityName();

        // Shift left values
        StringBuilder sbLeft = new StringBuilder();
        sbLeft.append("update ").append(entityName).append(" n")
                .append(" set n.").append(leftFieldName).append(" = n.").append(leftFieldName).append(" + :a")
                .append(" where n.").append(leftFieldName).append(" >= :b");

        if (last > 0) {
            sbLeft.append(" and n.").append(leftFieldName).append(" <= :c");
        }

        if (rootIdFieldName != null) {
            sbLeft.append(" and n.").append(rootIdFieldName).append(" = :d");
        }

        Query qLeft = nsm.getEntityManager().createQuery(sbLeft.toString());
        qLeft.setParameter("a", delta);
        qLeft.setParameter("b", first);
        if (last > 0) {
            qLeft.setParameter("c", last);
        }
        if (rootIdFieldName != null) {
            qLeft.setParameter("d", rootId);
        }
        qLeft.executeUpdate();

        // Shift right values
        StringBuilder sbRight = new StringBuilder();
        sbRight.append("update ").append(entityName).append(" n")
                .append(" set n.").append(rightFieldName).append(" = n.").append(rightFieldName).append(" + :a")
                .append(" where n.").append(rightFieldName).append(" >= :b");

        if (last > 0) {
            sbRight.append(" and n.").append(rightFieldName).append(" <= :c");
        }

        if (rootIdFieldName != null) {
            sbRight.append(" and n.").append(rootIdFieldName).append(" = :d");
        }

        Query qRight = nsm.getEntityManager().createQuery(sbRight.toString());
        qRight.setParameter("a", delta);
        qRight.setParameter("b", first);
        if (last > 0) {
            qRight.setParameter("c", last);
        }
        if (rootIdFieldName != null) {
            qRight.setParameter("d", rootId);
        }
        qRight.executeUpdate();
    }

    @Override public T unwrap() {
        return this.node;
    }

    public boolean isLeaf() {
        return !hasChildren();
    }

    @Override
    public Node<T> getFirstChild() {
        CriteriaBuilder cb = nsm.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = getBaseQuery();
        cq.where(cb.equal(queryRoot.get(nsm.getConfig(this.type).getLeftFieldName()), getLeftValue() + 1));

        nsm.applyRootId(this.type, cq, getRootValue());

        return nsm.getNode(nsm.getEntityManager().createQuery(cq).getSingleResult());
    }

    @Override
    public Node<T> getLastChild() {
        CriteriaBuilder cb = nsm.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = getBaseQuery();
        cq.where(cb.equal(queryRoot.get(nsm.getConfig(this.type).getRightFieldName()), getRightValue() - 1));

        nsm.applyRootId(this.type, cq, getRootValue());

        return nsm.getNode(nsm.getEntityManager().createQuery(cq).getSingleResult());
    }

    @Override
    public List<Node<T>> getAncestors() {
        CriteriaBuilder cb = nsm.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> cq = getBaseQuery();
        Predicate wherePredicate = cb.and(
                cb.lt(queryRoot.<Number>get(nsm.getConfig(this.type).getLeftFieldName()), getLeftValue()),
                cb.gt(queryRoot.<Number>get(nsm.getConfig(this.type).getRightFieldName()), getRightValue())
                );

        cq.where(wherePredicate);
        cq.orderBy(cb.asc(queryRoot.get(nsm.getConfig(this.type).getLeftFieldName())));

        nsm.applyRootId(this.type, cq, getRootValue());

        List<Node<T>> nodes = new ArrayList<Node<T>>();

        for (T n : nsm.getEntityManager().createQuery(cq).getResultList()) {
            nodes.add(nsm.getNode(n));
        }

        return nodes;
    }

    @Override
    public boolean isDescendantOf(Node<T> subj) {
        return ((getLeftValue() > subj.getLeftValue()) &&
                (getRightValue() < subj.getRightValue()) &&
                (getRootValue() == subj.getRootValue()));
    }

    public String getPath(String seperator) {
        StringBuilder path = new StringBuilder();
        List<Node<T>> ancestors = getAncestors();
        for (Node<T> ancestor : ancestors) {
            path.append(ancestor.toString()).append(seperator);
        }

        return path.toString();
    }

    @Override
    public void moveAsPrevSiblingOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot move node as previous sibling of itself");
        }
        if(dest.getLeftValue() > this.node.getLeftValue() && dest.getRightValue() < this.node.getRightValue() ){
            throw new IllegalArgumentException("Cannot move node as previous sibling of itself descendant");
        }
        if (dest.getRootValue() != getRootValue()) {
            throw new IllegalArgumentException("Cannot move node to another tree");
            //moveBetweenTrees(dest, dest.getLeftValue(), 1);
        } else {
            // Move within the tree
            int oldLevel = getLevel();
            setLevel(dest.getLevel());
            updateNode(dest.getLeftValue(), getLevel() - oldLevel);
        }
    }

    /**
     * move node's and its children to location 'destLeft' and update rest of tree.
     *
     * @param destLeft destination left mask
     * @param levelDiff
     */
    private synchronized void updateNode(int destLeft, int levelDiff) {
        int left = getLeftValue();
        int right = getRightValue();
        int rootId = getRootValue();
        int treeSize = right - left + 1;
        // Make room in the new branch
        shiftRLValues(destLeft, 0, treeSize, rootId);

        // src was shifted too?
        if (left >= destLeft) {
            left += treeSize;
            right += treeSize;
        }

        String levelFieldName = nsm.getConfig(this.type).getLevelFieldName();
        String leftFieldName = nsm.getConfig(this.type).getLeftFieldName();
        String rightFieldName = nsm.getConfig(this.type).getRightFieldName();
        String rootIdFieldName = nsm.getConfig(this.type).getRootIdFieldName();
        String entityName =  nsm.getConfig(this.type).getEntityName();

        // update level for descendants
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("update ").append(entityName).append(" n")
                .append(" set n.").append(levelFieldName).append(" = n.").append(levelFieldName).append(" + :a")
                .append(" where n.").append(leftFieldName).append(" >= :b")
                .append(" and n.").append(rightFieldName).append(" <= :c");

        if (rootIdFieldName != null) {
            updateQuery.append(" and n.").append(rootIdFieldName).append(" = :d");
        }

        Query q = nsm.getEntityManager().createQuery(updateQuery.toString());
        q.setParameter("a", levelDiff);
        q.setParameter("b", left);
        q.setParameter("c", right);
        if (rootIdFieldName != null) {
            q.setParameter("d", rootId);
        }
        q.executeUpdate();

        // now there's enough room next to target to move the subtree
        shiftRLValues(left, right, destLeft - left, rootId);

        // correct values after source (close gap in old tree)
        shiftRLValues(right + 1, 0, -treeSize, rootId);
    }

    @Override
    public void moveAsNextSiblingOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot move node as next sibling of itself");
        }
        if(dest.getLeftValue() > this.node.getLeftValue() && dest.getRightValue() < this.node.getRightValue() ){
            throw new IllegalArgumentException("Cannot move node as next sibling of itself descendant");
        }
        if (dest.getRootValue() != getRootValue()) {
            throw new IllegalArgumentException("Cannot move node to another tree");
            //moveBetweenTrees(dest, dest.getRightValue() + 1, 3);
        } else {
            // Move within tree
            int oldLevel = getLevel();
            setLevel(dest.getLevel());
            updateNode(dest.getRightValue() + 1, getLevel() - oldLevel);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveAsFirstChildOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot move node as first child of itself");
        }

        if(dest.getLeftValue() > this.node.getLeftValue() && dest.getRightValue() < this.node.getRightValue() ){
            throw new IllegalArgumentException("Cannot move node as first child of itself descendant");
        }

        if (dest.getRootValue() != getRootValue()) {
            throw new IllegalArgumentException("Cannot move node to another tree");
            //moveBetweenTrees(dest, dest.getLeftValue() + 1, 2);
        } else {
            // Move within tree
            int oldLevel = getLevel();
            setLevel(dest.getLevel() + 1);
            updateNode(dest.getLeftValue() + 1, getLevel() - oldLevel);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void moveAsLastChildOf(Node<T> dest) {
        if (dest == this.node) {
            throw new IllegalArgumentException("Cannot move node as last child of itself");
        }

        if(dest.getLeftValue() > this.node.getLeftValue() && dest.getRightValue() < this.node.getRightValue() ){
            throw new IllegalArgumentException("Cannot move node as last child of itself descendant");
        }


        if (dest.getRootValue() != getRootValue()) {
            throw new IllegalArgumentException("Cannot move node to another tree");
            //moveBetweenTrees(dest, dest.getLeftValue() + 1, 4);
        } else {
            // Move within tree
            int oldLevel = getLevel();
            setLevel(dest.getLevel() + 1);
            updateNode(dest.getRightValue(), getLevel() - oldLevel);
        }
    }

    /**
     * Accomplishes moving of nodes between different trees.
     * Used by the move* methods if the root values of the two nodes are different.
     *
     * @param dest
     * @param newLeftValue
     * @param moveType
     */
    private void moveBetweenTrees(Node<T> dest, int newLeftValue, int moveType) {

    	Configuration cfg = nsm.getConfig(this.type);
        String leftFieldName = cfg.getLeftFieldName();
        String rightFieldName = cfg.getRightFieldName();
        String levelFieldName = cfg.getLevelFieldName();
        String rootIdFieldName = cfg.getRootIdFieldName();
        String entityName = cfg.getEntityName();

        // Move between trees: Detach from old tree & insert into new tree
        int newRoot = dest.getRootValue();
        int oldRoot = getRootValue();
        int oldLft = getLeftValue();
        int oldRgt = getRightValue();
        int oldLevel = getLevel();

        // Prepare target tree for insertion, make room
        shiftRLValues(newLeftValue, 0, oldRgt - oldLft - 1, newRoot);

        // Set new root id for this node
        setRootValue(newRoot);
        //$this ->  _node ->  save();
        // Insert this node as a new node
        setRightValue(0);
        setLeftValue(0);

        switch (moveType) {
            case PREV_SIBLING:
                insertAsPrevSiblingOf(dest);
                break;
            case FIRST_CHILD:
                insertAsFirstChildOf(dest);
                break;
            case NEXT_SIBLING:
                insertAsNextSiblingOf(dest);
                break;
            case LAST_CHILD:
                insertAsLastChildOf(dest);
                break;
            default:
                throw new IllegalArgumentException("Unknown move operation: " + moveType);
        }

        setRightValue(getLeftValue() + (oldRgt - oldLft));

        int newLevel = getLevel();
        int levelDiff = newLevel - oldLevel;

        // Relocate descendants of the node
        int diff = getLeftValue() - oldLft;

        // Update lft/rgt/root/level for all descendants
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("update ").append(entityName).append(" n")
                .append(" set n.").append(leftFieldName).append(" = n.").append(leftFieldName).append(" + :a")
                .append(", n.").append(rightFieldName).append(" = n.").append(rightFieldName).append(" + :b")
                .append(", n.").append(levelFieldName).append(" = n.").append(levelFieldName).append(" + :c")
                .append(", n.").append(rootIdFieldName).append(" = :d")
                .append(" where n.").append(leftFieldName).append(" > :e")
                .append(" and n.").append(rightFieldName).append(" < :f")
                .append(" and n.").append(rootIdFieldName).append(" = :g");

        Query q = nsm.getEntityManager().createQuery(updateQuery.toString());
        q.setParameter("a", diff);
        q.setParameter("b", diff);
        q.setParameter("c", levelDiff);
        q.setParameter("d", newRoot);
        q.setParameter("e", oldLft);
        q.setParameter("f", oldRgt);
        q.setParameter("g", oldRoot);

        q.executeUpdate();

        // Close gap in old tree
        int first = oldRgt + 1;
        int delta = oldLft - oldRgt - 1;
        shiftRLValues(first, 0, delta, oldRoot);
    }

    @Override
    public void makeRoot(int newRootId) {
        if (isRoot()) {
            return;
        }

        Configuration cfg = nsm.getConfig(this.type);
        String leftFieldName = cfg.getLeftFieldName();
        String rightFieldName = cfg.getRightFieldName();
        String levelFieldName = cfg.getLevelFieldName();
        String rootIdFieldName = cfg.getRootIdFieldName();
        String entityName =  cfg.getEntityName();

        int oldRgt = getRightValue();
        int oldLft = getLeftValue();
        int oldRoot = getRootValue();
        int oldLevel = getLevel();

        // Update descendants lft/rgt/root/level values
        int diff = 1 - oldLft;
        int newRoot = newRootId;

        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("update ").append(entityName).append(" n")
                .append(" set n.").append(leftFieldName).append(" = n.").append(leftFieldName).append(" + :a")
                .append(", n.").append(rightFieldName).append(" = n.").append(rightFieldName).append(" + :b")
                .append(", n.").append(levelFieldName).append(" = n.").append(levelFieldName).append(" - :c")
                .append(", n.").append(rootIdFieldName).append(" = :d")
                .append("where n.").append(leftFieldName).append(" > :e")
                .append(" and n.").append(rightFieldName).append(" < :f")
                .append(" and n.").append(rootIdFieldName).append(" = :g");

        Query q = nsm.getEntityManager().createQuery(updateQuery.toString());
        q.setParameter("a", diff);
        q.setParameter("b", diff);
        q.setParameter("c", oldLevel);
        q.setParameter("d", newRoot);
        q.setParameter("e", oldLft);
        q.setParameter("f", oldRgt);
        q.setParameter("g", oldRoot);

        q.executeUpdate();

        // Detach from old tree (close gap in old tree)
        int first = oldRgt + 1;
        int delta = oldLft - oldRgt - 1;
        shiftRLValues(first, 0, delta, getRootValue());

        // Set new lft/rgt/root/level values for root node
        setLeftValue(1);
        setRightValue(oldRgt - oldLft + 1);
        setRootValue(newRootId);
        setLevel(0);
    }
}
