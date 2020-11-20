package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.dao.OrganizationRepository;
import com.pactera.esc.core.dao.ResourceRepository;
import com.pactera.esc.core.dao.RoleRepository;
import com.pactera.esc.core.dao.UserRepository;
import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.dto.mapper.ResourceMapper;
import com.pactera.esc.core.dto.mapper.RoleMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.BusinessException;
import com.pactera.esc.core.exception.ItemNotFoundException;
import com.pactera.esc.core.exception.ItemRepeatException;
import com.pactera.esc.core.util.CommonUtil;
import com.pactera.esc.sys.service.RoleService;
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
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.stream.Collectors;

import static com.pactera.esc.core.common.Constants.UserType.ADMINISTRATOR;

/**
 * 角色
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    private final OrganizationRepository organizationRepository;

    @Override
    public Role get(String id) throws BaseException {
        Optional<Role> optional = roleRepository.findById(id);
        return optional.orElseThrow(() -> new BaseException(BaseErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public Role find(Role entity) throws BaseException {
        Optional<Role> optional = roleRepository.findOne(this.getSpecification(entity));
        return optional.orElse(null);
    }

    @Override
    public List<Role> list(Role entity) throws BaseException {
        return roleRepository.findAll(this.getSpecification(entity), Sort.by(entity.getSortParam().getDirection(),
            entity.getSortParam().getField()));
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Page<Role> page(Role entity) throws BaseException {
        //page模糊查询
        entity.setNameLike(entity.getName());
        entity.setName(null);
        Pageable pageable = PageRequest.of(entity.getPageable().getPageNo(), entity.getPageable().getPageSize(),
            Sort.by(entity.getSortParam().getDirection(), entity.getSortParam().getField()));
        return roleRepository.findAll(this.getSpecification(entity), pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(RoleDTO roleDTO) throws BaseException {
        Role role = roleMapper.toEntity(roleDTO);
        this.checkUniqueForCreate(role);
        roleRepository.save(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RoleDTO roleDTO) throws BaseException {
        Role oldRole = get(roleDTO.getId());
        if (!Objects.equals(roleDTO.getName(), oldRole.getName())) {
            checkName(roleDTO.getName());
        }
        roleMapper.update(oldRole, roleDTO);

        roleRepository.save(oldRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws BaseException {
        Role entity = this.get(id);

        if (!entity.getOrganizations().isEmpty() && !entity.getUsers().isEmpty()) {
            throw new BusinessException(BaseErrorCode.NOT_DELETE_IN_USE);
        }

        roleRepository.delete(entity);
    }

    @Override
    public List<ResourceDTO> getResourceTree(User loginUser) {
        List<Resource> resources;
        if(ADMINISTRATOR.getType() !=loginUser.getType()) {
            resources = loginUser.getRoles()
                .stream().flatMap(role -> role.getResources().stream())
                .distinct()
                .collect(Collectors.toList());
        } else {
            resources = resourceRepository.findAll();
        }
        return assemblyToTree(resources);
    }

    private List<ResourceDTO> assemblyToTree(List<Resource> resources) {
        Map<String, List<ResourceDTO>> parentIdResourceMap = resources.stream().map(resourceMapper::toDTO)
            .collect(Collectors.groupingBy(ResourceDTO::getParentId));

        return parentIdResourceMap.get("0").stream()
            .sorted(Comparator.comparingInt(ResourceDTO::getSort))
            .peek(res -> appendChildren(res, parentIdResourceMap))
            .collect(Collectors.toList());
    }

    private void appendChildren(ResourceDTO dto, Map<String, List<ResourceDTO>> parentIdResourceMap) {
        if(!parentIdResourceMap.containsKey(dto.getId())) {
            return;
        }

        List<ResourceDTO> children = parentIdResourceMap.get(dto.getId()).stream()
            .sorted(Comparator.comparingInt(ResourceDTO::getSort))
            .peek(res -> appendChildren(res, parentIdResourceMap))
            .collect(Collectors.toList());

        dto.setChildren(children);
    }

    @Override
    public List<RoleDTO> getRolesExclusionAdmin() {
        return this.list(new Role()).stream()
            .map(roleMapper::toDTO)
            .filter(role -> !Objects.equals("1", role.getId()))
            .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> getRolesByOrg(String organizationId) {
        Organization organization = organizationRepository.findById(organizationId).orElseThrow(ItemNotFoundException::new);
        return organization.getRoles().stream()
            .map(roleMapper::toDTO)
            .collect(Collectors.toList());
    }

    private List<ResourceDTO> getResourceForAdmin(String roleId, String type) {
        List<ResourceDTO> resources;
        resources = resourceRepository.findByParentId("0").stream()
            .map(resourceMapper::toDTO)
            .sorted(Comparator.comparingInt(ResourceDTO::getSort))
            .collect(Collectors.toList());
        if (Objects.equals(Constants.ROLE_UPDATE, type)) {
            return getResourceSelected(roleId, resources);
        }
        return getResourceTreeByChildren(resources, null);
    }

    private List<ResourceDTO> getResourceForOrg(String userId, String roleId, String type) {
        List<ResourceDTO> resources;
        resources = getResourceOnOrg(userId);
        if (Objects.equals(Constants.ROLE_UPDATE, type)) {
            return getResourceSelected(roleId, resources);
        }
        return getResourceTreeByChildren(resources, null);
    }

    private List<ResourceDTO> getResourceSelected(String roleId, List<ResourceDTO> resources) {
        Objects.requireNonNull(roleId);
        List<ResourceDTO> existResources = this.getResourceByRoleId(roleId);
        this.setResourceSelected(resources, existResources);
        return getResourceTreeByChildren(resources, existResources);
    }

    private List<ResourceDTO> getResourceOnOrg(String userId) {
        List<ResourceDTO> resources;
        Organization org = userRepository.findById(userId).orElseThrow(ItemNotFoundException::new).getOrganization();
        resources = org.getRoles().stream()
            .flatMap(role -> role.getResources().stream())
            .map(resourceMapper::toDTO)
            .filter(resource -> Objects.equals("0", resource.getParentId()))
            .distinct()
            .sorted(Comparator.comparingInt(ResourceDTO::getSort))
            .collect(Collectors.toList());
        return resources;
    }

    private void setResourceSelected(List<ResourceDTO> resources, List<ResourceDTO> existResources) {
        if (!CollectionUtils.isEmpty(existResources)) {
            resources.forEach(re -> {
                if (existResources.contains(re)) {
                    List<Resource> byParentId = resourceRepository.findByParentId(re.getId());
                    if (byParentId.size() == 0) {
                        re.setSelected(true);
                    }
                }
            });
        }
    }

    private List<ResourceDTO> getResourceByRoleId(String roleId) {
        return roleRepository.findById(roleId).get().getResources().stream().map(resourceMapper::toDTO)
            .collect(Collectors.toList());
    }

    private List<ResourceDTO> getResourceTreeByChildren(List<ResourceDTO> children, List<ResourceDTO> exist) {
        children.forEach((child) -> {
            List<ResourceDTO> resources = resourceRepository.findByParentId(child.getId()).stream()
                .map(resourceMapper::toDTO)
                .sorted(Comparator.comparingInt(ResourceDTO::getSort))
                .collect(Collectors.toList());
            setResourceSelected(resources, exist);
            child.setChildren(resources);
            getResourceTreeByChildren(resources, exist);
        });
        return children;
    }

    /**
     * 校验唯一约束:新增
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForCreate(Role entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName())) {
            Role param = new Role();
            param.setName(entity.getName());
            if (CommonUtil.isNotEmptyList(this.list(param))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 校验唯一约束:修改
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForUpdate(Role entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName())) {
            Role param = new Role();
            param.setName(entity.getName());
            param.setIdNe(entity.getId());
            if (CommonUtil.isNotEmptyList(this.list(param))) {
                throw new BusinessException(BaseErrorCode.NAME_IS_USED);
            }
        }
    }

    /**
     * 动态条件查询
     */
    @SuppressWarnings("Duplicates")
    private Specification<Role> getSpecification(Role entity) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
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
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }

    private void checkName(String name) {
        roleRepository.findRoleByName(name).ifPresent(repeat -> {
            throw new ItemRepeatException(BaseErrorCode.NAME_IS_USED);
        });
    }
}
