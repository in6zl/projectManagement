package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author si.chen
 * @date 2020/3/27 15:20
 */
public interface ResourceService {
    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Resource get(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    ResourceDTO detail(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    ResourceDTO find(Resource entity) throws BaseException;

    /**
     * 查询多个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<ResourceDTO> list(Resource entity) throws BaseException;

    /**
     * 查询分页
     *
     * @param entity 实体信息
     * @return 实体信息
     */
    Page<ResourceDTO> page(Resource entity);

    /**
     * 新建
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Resource create(Resource entity) throws BaseException;

    /**
     * 修改
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Resource update(Resource entity) throws BaseException;

    /**
     * 删除
     *
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 根据父id获取
     * @param parentId
     * @return
     */
    List<ResourceDTO> getResourceByParentId(String parentId);

    /**
     * 获取所有2级菜单
     * @return
     */
    List<ResourceDTO> getSecondLevelResource();
}
