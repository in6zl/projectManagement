package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 角色
 */
public interface RoleService {
    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Role get(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Role find(Role entity) throws BaseException;

    /**
     * 查询多个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<Role> list(Role entity) throws BaseException;

    /**
     * 查询分页
     *
     * @param entity 实体信息
     * @return 实体信息
     */
    Page<Role> page(Role entity);

    /**
     * 新建
     *
     * @param roleDTO 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    void create(RoleDTO roleDTO) throws BaseException;

    /**
     * 修改
     *
     * @param roleDTO roleDTO
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    void update(RoleDTO roleDTO) throws BaseException;

    /**
     * 删除
     *
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 获取资源树
     *
     * @return List<ResourceDTO>
     * @param loginUser
     */
    List<ResourceDTO> getResourceTree(User loginUser);

    /**
     * 获取角色（超级管理员除外）
     *
     * @return
     */
    List<RoleDTO> getRolesExclusionAdmin();

    /**
     * 根据组织获取角色
     *
     * @param organizationId
     * @return
     */
    List<RoleDTO> getRolesByOrg(String organizationId);
}
