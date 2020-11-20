package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.dao.DictRepository;
import com.pactera.esc.core.dao.DictTypeRepository;
import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import com.pactera.esc.core.dto.mapper.DictTypeMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.util.BeanUtils;
import com.pactera.esc.sys.service.DictTypeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pactera.esc.core.common.BaseErrorCode.NAME_HAVE;

/**
 * @author 陸文
 * @title: DictTypeServiceImpl
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2613:38
 */
@Service
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeRepository dictTypeRepository;

    private final DictTypeMapper dictTypeMapper;

    private final DictRepository dictRepository;

    public DictTypeServiceImpl(DictTypeRepository dictTypeRepository, DictTypeMapper dictTypeMapper, DictRepository dictRepository) {
        this.dictTypeRepository = dictTypeRepository;
        this.dictTypeMapper = dictTypeMapper;
        this.dictRepository = dictRepository;
    }

    @Override
    public void create(DictType dictType) throws BaseException {
        Optional<DictType> optional = dictTypeRepository.findByName(dictType.getName());
        if(optional.isPresent()){
            throw new BaseException(BaseErrorCode.NAME_HAVE.getCode(),
                BaseErrorCode.NAME_HAVE.getMessage());
        }
        dictTypeRepository.save(dictType);
    }

    @Override
    public void delete(String id) throws BaseException {
        List<Dict> optional = dictRepository.findByDictType_Id(id);
        if(optional.size()!=0){
            throw new BaseException(BaseErrorCode.DICT_HAVE.getCode(),
                BaseErrorCode.DICT_HAVE.getMessage());
        }
        this.get(id);
        dictTypeRepository.deleteById(id);
    }

    @Override
    public DictType find(DictType entity) throws BaseException {
        Optional<DictType> optional = dictTypeRepository.findOne(this.getSpecification(entity));
        return optional.orElse(null);
    }

    @Override
    public DictType get(String id) throws BaseException {
        Optional<DictType> optional = dictTypeRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new BaseException(BaseErrorCode.BEAN_NOT.getCode(),
                BaseErrorCode.BEAN_NOT.getMessage());
        }
    }

    @Override
    public DictType update(DictType dictType) throws BaseException {
        Optional<DictType> optional = dictTypeRepository.findByNameAndIdNot(dictType.getName(),dictType.getId());
        if(optional.isPresent()){
            throw new BaseException(BaseErrorCode.NAME_HAVE.getCode(),
                BaseErrorCode.NAME_HAVE.getMessage());
        }
        DictType entity = this.get(dictType.getId());
        BeanUtils.copyNotNullProperties(dictType, entity);
        return dictTypeRepository.save(entity);
    }

    @Override
    public List<DictTypeDTO> list(DictType dictType) throws BaseException {
        return dictTypeRepository.findAll(this.getSpecification(dictType)).stream()
            .map(dictTypeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Page<DictTypeDTO> page(DictType dictType) {
        Pageable pageable = PageRequest.of(dictType.getPageable().getPageNo(), dictType.getPageable().getPageSize());
        return dictTypeRepository.findAll(this.getSpecification(dictType), pageable).map(dictTypeMapper::toDTO);
    }

    @Override
    public void multipleDictType(DictTypeDTO dictTypeDTO) {
        if (dictTypeDTO.getName() != null) {
            Optional<DictType> dictType = dictTypeRepository.findByName(dictTypeDTO.getName());
            if (dictTypeDTO.getId() == null) {
                if (dictType.isPresent()) {
                    throw new BaseException(NAME_HAVE);
                }
            } else {
                if (dictType.isPresent()) {
                    if (!dictTypeDTO.getId().equals(dictType.get().getId())) {
                        throw new BaseException(NAME_HAVE);
                    }
                }
            }
        }
    }

    @SuppressWarnings("Duplicates")
    private Specification<DictType> getSpecification(DictType dictType) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(dictType.getId())) {
                Predicate predicate = cb.equal(root.get("id"), dictType.getId().trim());
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(dictType.getName())) {
                Predicate predicate = cb.like(root.get("name"), "%" + dictType.getName().trim() + "%");
                list.add(predicate);
            }
            query.orderBy(cb.asc(root.get("updateTime")));
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }
}
