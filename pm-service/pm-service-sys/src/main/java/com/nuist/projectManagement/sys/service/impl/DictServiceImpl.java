package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.dao.DictRepository;
import com.pactera.esc.core.dao.DictTypeRepository;
import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictDTO;
import com.pactera.esc.core.dto.mapper.DictMapper;
import com.pactera.esc.core.dto.mapper.DictTypeMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.ItemNotFoundException;
import com.pactera.esc.core.util.BeanUtils;
import com.pactera.esc.sys.service.DictService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author 陸文
 * @title: DictServiceImpl
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2713:46
 */
@Service
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository;

    private final DictMapper dictMapper;

    @Resource
    private DictTypeRepository dictTypeRepository;

    public DictServiceImpl(DictRepository dictRepository, DictMapper dictMapper) {
        this.dictRepository = dictRepository;
        this.dictMapper = dictMapper;
    }


    @Override
    public void create(Dict dict) throws BaseException {
        List<Dict> dicts = dictRepository.findByDictType_Id(dict.getDictType().getId());
        dicts.forEach(d -> {
            if (d.getDictKey().equals(dict.getDictKey())) {
                throw new BaseException(BaseErrorCode.KEY_HAVE);
            }
            if (d.getDictValue().equals(dict.getDictValue())) {
                throw new BaseException(BaseErrorCode.VALUE_HAVE);
            }
        });
        dictRepository.save(dict);
    }

    @Override
    public void delete(String id) throws BaseException {
        this.get(id);
        dictRepository.deleteById(id);
    }

    @Override
    @SuppressWarnings("Duplicates")
    public Dict get(String id) throws BaseException {
        Optional<Dict> optional = dictRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new BaseException(BaseErrorCode.BEAN_NOT.getCode(),
                BaseErrorCode.BEAN_NOT.getMessage());
        }
    }

    @Override
    public Dict update(Dict dict) throws BaseException {
        List<Dict> dicts = dictRepository.findByDictType_Id(dict.getDictType().getId());
        dicts.forEach(d -> {
            if (d.getDictKey().equals(dict.getDictKey()) && !d.getId().equals(dict.getId())) {
                throw new BaseException(BaseErrorCode.KEY_HAVE);
            }
            if (d.getDictValue().equals(dict.getDictValue()) && !d.getId().equals(dict.getId())) {
                throw new BaseException(BaseErrorCode.VALUE_HAVE);
            }
        });
        Dict dict1 = dictRepository.findById(dict.getId()).orElseThrow(() -> new BaseException(BaseErrorCode.ITEM_NOT_FOUND));
        dict1.setDictKey(dict.getDictKey());
        dict1.setDictType(dict.getDictType());
        dict1.setDictValue(dict.getDictValue());
        return dictRepository.save(dict1);
    }

    @Override
    public List<DictDTO> list(Dict dict) throws BaseException {
        return dictRepository.findAll(this.getSpecification(dict)).stream()
            .map(dictMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Page<DictDTO> page(Dict dict) {
        Pageable pageable = PageRequest.of(dict.getPageable().getPageNo(), dict.getPageable().getPageSize());
        return dictRepository.findAll(this.getSpecification(dict), pageable).map(dictMapper::toDTO);
    }

    @Override
    public String getValue(String key) {
        return dictRepository.findByDictKey(key).orElseThrow(() -> new ItemNotFoundException(
            BaseErrorCode.BEAN_NOT.getMessage())).getDictValue();
    }

    @Override
    public List<DictDTO> getByDictType(String type) {
        List<Dict> dicts = dictRepository.findByDictTypeName(type);
        return dicts.stream().map(dictMapper::toDTO).collect(Collectors.toList());
    }

    @SuppressWarnings("Duplicates")
    private Specification<Dict> getSpecification(Dict dict) {
        return (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (StringUtils.isNotBlank(dict.getId())) {
                Predicate predicate = cb.equal(root.get("id"), dict.getId().trim());
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(dict.getDictKey())) {
                Predicate predicate = cb.like(root.get("dictKey"), "%" + dict.getDictKey().trim() + "%");
                list.add(predicate);
            }
            if (StringUtils.isNotBlank(dict.getDictValue())) {
                Predicate predicate = cb.like(root.get("dictValue"), "%" + dict.getDictValue().trim() + "%");
                list.add(predicate);
            }
            if (dict.getDictType() != null && StringUtils.isNotBlank(dict.getDictType().getName())) {
                Predicate predicate = cb.like(root.get("dictType").get("name"), "%" + dict.getDictType().getName() + "%");
                list.add(predicate);
            }
            query.orderBy(cb.asc(root.get("updateTime")));
            query.where(list.toArray(new Predicate[0]));
            return query.getRestriction();
        };
    }
}
