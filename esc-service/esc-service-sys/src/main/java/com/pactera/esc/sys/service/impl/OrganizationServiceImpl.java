package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.SendTypeEnum;
import com.pactera.esc.core.dao.OrganizationRepository;
import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.dto.mapper.OrganizationMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.BusinessException;
import com.pactera.esc.core.exception.ItemNotFoundException;
import com.pactera.esc.core.exception.ItemRepeatException;
import com.pactera.esc.core.tree.JpaNestedSetManager;
import com.pactera.esc.core.tree.Node;
import com.pactera.esc.core.util.CommonUtil;
import com.pactera.esc.sys.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import static com.pactera.esc.core.common.BaseErrorCode.ITEM_IS_USED;
import static com.pactera.esc.core.common.BaseErrorCode.NOT_DELETE_IN_USE;

/**
 * @author si.chen
 * @date 2020/3/27 15:13
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private JpaNestedSetManager nsm;
    private OrganizationMapper organizationMapper;

    @Override
    public OrganizationDTO get(String id) throws BaseException {
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new BaseException(BaseErrorCode.ITEM_NOT_FOUND));
        OrganizationDTO dto = organizationMapper.toDTO(organization);
        return dto;
    }

    @Override
    public Organization find(Organization entity) throws BaseException {
        Optional<Organization> optional = organizationRepository.findOne(this.getSpecification(entity, null));
        return optional.orElse(null);
    }

    @Override
    public List<OrganizationDTO> list(Organization entity, User loginUser) throws BaseException {
        return organizationRepository.findAll(this.getSpecification(entity, loginUser), Sort.by(entity.getSortParam().getDirection(),
            entity.getSortParam().getField())).stream().map(organizationMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Page<Organization> page(Organization entity) {
        //page模糊查询
        entity.setNameLike(entity.getName());
        entity.setName(null);
        Pageable pageable = PageRequest.of(entity.getPageable().getPageNo(), entity.getPageable().getPageSize(),
            Sort.by(entity.getSortParam().getDirection(), entity.getSortParam().getField()));
        return organizationRepository.findAll(this.getSpecification(entity, null), pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization create(Organization entity, String parentId, String[] roles) throws BaseException {
        Optional<Organization> org = organizationRepository.findByName(entity.getName())
            .filter(o -> !o.getId().equals(entity.getId()));
        if (org.isPresent())
            throw new ItemRepeatException("组织名称重复");
        Organization organization=StringUtils.isBlank(parentId) ? createRoot(entity) : addChild(parentId, entity);
        for (String id:roles){
            organizationRepository.addOrganization(organization.getId(),id);
        }
        return organization;
    }

    @Override
    public void findByName(OrganizationDTO organizationDTO) {
        Optional<Organization> org = organizationRepository.findByName(organizationDTO.getName());
        if (organizationDTO.getId() == null) {
            if (org.isPresent()) {
                throw new BaseException(ITEM_IS_USED);
            }
        } else {
            if (org.isPresent()) {
                if (!organizationDTO.getId().equals(org.get().getId())) {
                    throw new BaseException(ITEM_IS_USED);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization update(Organization entity, String parentId, String[] roles) throws BaseException {
        organizationRepository.deleteOrganization(entity.getId());
        Organization exist = organizationRepository.findById(entity.getId()).orElseThrow(ItemNotFoundException::new);
        this.checkUniqueForUpdate(entity, exist);
        OrganizationDTO organizationDTO = organizationMapper.toDTO(entity);
        BeanCopier copy = BeanCopier.create(OrganizationDTO.class, Organization.class, false);
        copy.copy(organizationDTO, exist, null);
        //TODO 更新操作。。。
        Organization organization=organizationRepository.save(exist);
        for (String id:roles){
            organizationRepository.addOrganization(organization.getId(),id);
        }
        return organization;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws BaseException {
        Organization entity = organizationRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        //检查用户
        Node<Organization> node = nsm.getNode(entity);
        if(node.hasChildren() && !entity.getUsers().isEmpty()) {
            throw new BusinessException(NOT_DELETE_IN_USE);
        }
        this.delete(entity);
        organizationRepository.deleteOrganization(id);
    }

    @Override
    public List<Organization> listOrganization() {
        List<Node<Organization>> nodes = nsm.listNodes(Organization.class);
        return unwrapNodeList(nodes);
    }

    @Override
    public List<Organization> listOrganizationTrees() {
        List<Node<Organization>> nodes = nsm.listTrees(Organization.class);
        return unwrapNodeList(nodes);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization createRoot(Organization organization) {
        int rootId = 0;
        synchronized (this) {
            Integer curRootId = organizationRepository.findMaxRootId();
            if (null != curRootId) {
                rootId = ++curRootId;
            }
        }
        organization.setRootValue(rootId);
        Node<Organization> root = nsm.createRoot(organization);
        return root.unwrap();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization addChild(Organization parent, Organization son) {
        Node<Organization> parentNode = nsm.getNode(parent);
        return parentNode.addChild(son).unwrap();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Organization addChild(String parentId, Organization son) {
        Organization parentNode = organizationRepository.findById(parentId)
            .orElseThrow(() -> new ItemNotFoundException("父组织不存在"));
        return addChild(parentNode, son);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Organization organization) throws BaseException {
        nsm.getNode(organization).delete();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        getNodeById(id).delete();
    }

    @Override
    public Organization getParent(String id) {
        Node<Organization> node = getNodeById(id);
        if (null == node) {
            return null;
        }
        Node<Organization> parentNode = node.getParent();
        if (null == parentNode) {
            return null;
        }
        return parentNode.unwrap();
    }

    @Override
    public List<Organization> getChildren(String id) {
        Node<Organization> node = getNodeById(id);
        List<Node<Organization>> children = node.getChildren();
        return unwrapNodeList(children);
    }

    @Override
    public List<Organization> getAncestors(String id) {
        Node<Organization> node = getNodeById(id);
        List<Node<Organization>> ancestors = node.getAncestors();
        return unwrapNodeList(ancestors);
    }

    @Override
    public List<Organization> getDescendants(String id, int deep) {
        Node<Organization> node = getNodeById(id);
        List<Node<Organization>> descendants = node.getDescendants(deep);
        return unwrapNodeList(descendants);
    }

    private List<Organization> unwrapNodeList(List<Node<Organization>> nodes) {
        return nodes.stream().map(Node::unwrap).collect(Collectors.toList());
    }

    private Node<Organization> getNodeById(String id) {
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new ItemNotFoundException(String.format("Org [%s] not found", id)));
        return nsm.getNode(organization);
    }

    /**
     * 校验唯一约束:新增
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForCreate(Organization entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName())) {
            Organization param = new Organization();
            param.setName(entity.getName());
            if (CommonUtil.isNotEmptyList(this.list(param, null))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 校验唯一约束:修改
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForUpdate(Organization entity, Organization exist) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName()) && !exist.getName().equals(entity.getName())) {
            Organization param = new Organization();
            param.setName(entity.getName());
            param.setIdNe(entity.getId());
            if (CommonUtil.isNotEmptyList(this.list(param, null))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 动态条件查询
     */
    @SuppressWarnings("Duplicates")
    private Specification<Organization> getSpecification(Organization entity, User loginUser) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (!Objects.isNull(loginUser)) {
                if (!Constants.ADMIN_USER_ID.equals(loginUser.getId())) {
                    Optional.ofNullable(loginUser.getOrganization().getId()).ifPresent(id -> {
                        List<String> ids = getChildren(id).stream().map(Organization::getId).collect(Collectors.toList());
                        ids.add(id);
                        CriteriaBuilder.In<Object> in = cb.in(root.get("id"));
                        ids.forEach(i -> in.value(i));
                        list.add(in);
                    });
                }
            }
            //ID不等于
            if (StringUtils.isNotBlank(entity.getIdNe())) {
                Predicate predicate = cb.notEqual(root.get("id"), entity.getIdNe());
                list.add(predicate);
            }
            //like
            if (StringUtils.isNotBlank(entity.getNameLike())) {
                Predicate predicate = cb.like(root.get("name"), "%" + entity.getNameLike() + "%");
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(entity.getName())) {
                Predicate predicate = cb.equal(root.get("name"), entity.getName());
                list.add(predicate);
            }
            if (entity.getType() != null) {
                Predicate predicate = cb.equal(root.get("type"), entity.getType());
                list.add(predicate);
            }
            if (entity.getRootId() != null) {
                Predicate predicate = cb.equal(root.get("rootId"), entity.getRootId());
                list.add(predicate);
            }
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }
}
