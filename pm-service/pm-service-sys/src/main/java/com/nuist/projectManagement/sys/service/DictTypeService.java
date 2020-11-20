package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author 陸文
 * @title: DictTypeService
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2613:38
 */
public interface DictTypeService {

    /**
     * 创建字典类型
     * @param dictType dictType
     * @throws BaseException 基本异常
     */
    void create(DictType dictType) throws BaseException;

    /**
     * 删除字典类型
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    DictType find(DictType entity) throws BaseException;

    /**
     * 获取信息
     * @param id id
     * @return DictType
     * @throws BaseException 基本异常
     */
    DictType get(String id) throws BaseException;

    /**
     * 更新字典类型
     * @param dictType dictType
     * @return DictType
     * @throws BaseException 基本异常
     */
    DictType update(DictType dictType) throws BaseException;

    /**
     * 查询多个字典类型
     * @param dictType 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<DictTypeDTO> list(DictType dictType) throws BaseException;

    /**
     * 查询分页
     *
     * @param dictType 实体信息
     * @return 实体信息
     */
    Page<DictTypeDTO> page(DictType dictType);


    void multipleDictType(DictTypeDTO dictTypeDTO);
}
