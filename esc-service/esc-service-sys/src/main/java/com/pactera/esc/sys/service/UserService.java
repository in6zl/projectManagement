package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

/**
 * @author si.chen
 * @date 2020/3/26 16:57
 */
public interface UserService {


    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    User get(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    UserDTO detail(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    UserDTO find(User entity) throws BaseException;

    /**
     * 查询多个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<UserDTO> list(User entity) throws BaseException;

    /**
     * 查询分页
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Page<UserDTO> page(User entity,User loginUser) throws BaseException;

    /**
     * 新建
     *
     * @param userDTO 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    void create(UserDTO userDTO) throws BaseException;

    /**
     * 修改
     *
     * @param userDTO userDTO
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    void update(UserDTO userDTO) throws BaseException;

    /**
     * 修改密码-已登录
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    User updatePassword(User entity) throws BaseException;

    /**
     * 重置密码-超管操作
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    User resetPassword(String id) throws BaseException;

    /**
     * 删除
     *
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 获取当前用户
     *
     * @param user
     * @return
     */
    UserDTO currentUser(User user);

    /**
     * 获取用户角色
     *
     * @param user
     * @return
     */
    Set<Role> getRoles(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    UserDTO login(UserDTO user);

}
