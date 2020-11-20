package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.dto.DictDTO;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * @author 陸文
 * @title: DictService
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2713:46
 */
public interface DictService {

    /**
     * 创建字典表
     * @param dict dict
     * @throws BaseException 基本异常
     */
    void create(Dict dict) throws BaseException;

    /**
     * 删除字典表
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 获取信息
     * @param id id
     * @return Dict
     * @throws BaseException 基本异常
     */
    Dict get(String id) throws BaseException;

    /**
     * 更新字典表
     * @param dict dict
     * @return Dict
     * @throws BaseException 基本异常
     */
    Dict update(Dict dict) throws BaseException;

    /**
     * 查询dict
     * @param dict 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<DictDTO> list(Dict dict) throws BaseException;

    /**
     * 查询分页
     *
     * @param dict 实体信息
     * @return 实体信息
     */
    Page<DictDTO> page(Dict dict);


    /**
     * 获取字典值
     * @param key 字典键
     * @return 获取字典值
     */
    String getValue(String key);

    List<DictDTO> getByDictType(String type);
}
