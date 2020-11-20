package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.dao.ResourceRepository;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.mapper.ResourceMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.BusinessException;
import com.pactera.esc.core.util.BeanUtils;
import com.pactera.esc.core.util.CommonUtil;
import com.pactera.esc.sys.service.ResourceService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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

/**
 * @author si.chen
 * @date 2020/3/27 15:22
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;


    @Override
    public Resource get(String id) throws BaseException {
        Optional<Resource> optional = resourceRepository.findById(id);
        return optional.orElseThrow(() -> new BaseException(BaseErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public ResourceDTO detail(String id) throws BaseException {
        return resourceMapper.toDTO(this.get(id));
    }

    @Override
    public ResourceDTO find(Resource entity) throws BaseException {
        Optional<Resource> optional = resourceRepository.findOne(this.getSpecification(entity));
        return resourceMapper.toDTO(optional.orElse(null));
    }

    @Override
    public List<ResourceDTO> list(Resource entity) throws BaseException {
        return resourceRepository.findAll(this.getSpecification(entity), Sort.by(entity.getSortParam().getDirection(),
            entity.getSortParam().getField()))
            .stream().map(resourceMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Page<ResourceDTO> page(Resource entity) {
        //page模糊查询
        entity.setNameLike(entity.getName());
        entity.setName(null);
        Pageable pageable = PageRequest.of(entity.getPageable().getPageNo(), entity.getPageable().getPageSize(),
            Sort.by(entity.getSortParam().getDirection(), entity.getSortParam().getField()));
        return resourceRepository.findAll(this.getSpecification(entity), pageable).map(resourceMapper::toDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Resource create(Resource entity) throws BaseException {
        this.checkUniqueForCreate(entity);
        setParentIdAndSort(entity);
        return resourceRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Resource update(Resource resource) throws BaseException {
        Resource entity = this.get(resource.getId());
        this.checkUniqueForUpdate(entity);
        boolean isSort = false;
        if (!Objects.equals(resource.getParentId(), entity.getParentId())) {
            isSort = true;
        }
        BeanUtils.copyNotNullProperties(resource, entity);
        if (isSort) {
            setParentIdAndSort(entity);
        }
        return resourceRepository.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) throws BaseException {
        Resource entity = this.get(id);
        //判断角色资源
//        RoleResource param = new RoleResource();
//        param.setResource(entity);
        List<String> parentIdList=resourceRepository.getParentIds();
        if (parentIdList.contains(id)){
           throw new BaseException(BaseErrorCode.HAVE_PARENT_ID);
        }else {
//            if (CommonUtil.isNotEmptyList(roleResourceService.list(param))) {
//                roleResourceService.deleteByEntity(param);
//            }
            resourceRepository.delete(entity);
        }
    }

    @Override
    public List<ResourceDTO> getResourceByParentId(String parentId) {
        return resourceRepository.findByParentId(parentId).stream()
            .map(resourceMapper::toDTO)
            .sorted(Comparator.comparingInt(ResourceDTO::getSort))
            .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getSecondLevelResource() {
        Resource param = new Resource();
        param.setType(0);
        return this.list(param).stream().filter(o -> !Objects.equals("0", o.getParentId())).collect(Collectors.toList());
    }

    private void setParentIdAndSort(Resource entity) {
        List<ResourceDTO> list = getResourceByParentId(entity.getParentId());
        if (CollectionUtils.isEmpty(list)) {
            entity.setSort(0);
        } else {
            int sort = list.stream().mapToInt(ResourceDTO::getSort).max().getAsInt();
            entity.setSort(sort + 1);
        }
    }

    /**
     * 校验唯一约束:新增
     */
    @SuppressWarnings("Duplicates")
    private void checkUniqueForCreate(Resource entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName())) {
            Resource param = new Resource();
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
    private void checkUniqueForUpdate(Resource entity) throws BaseException {
        if (StringUtils.isNotBlank(entity.getName())) {
            Resource param = new Resource();
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
    private Specification<Resource> getSpecification(Resource entity) {
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
            if(StringUtils.isNotBlank(entity.getUrl())){
                Predicate predicate=cb.equal(root.get("url"),entity.getUrl());
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(entity.getParentId())) {
                Predicate predicate = cb.equal(root.get("parentId"), entity.getParentId());
                list.add(predicate);
            }
            if (entity.getLevel() != null) {
                Predicate predicate = cb.equal(root.get("level"), entity.getLevel());
                list.add(predicate);
            }
            if (entity.getType() != null) {
                Predicate predicate = cb.equal(root.get("type"), entity.getType());
                list.add(predicate);
            }
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }
}
