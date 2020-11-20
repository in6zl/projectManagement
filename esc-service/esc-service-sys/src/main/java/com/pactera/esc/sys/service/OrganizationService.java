package com.pactera.esc.sys.service;

import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.exception.BaseException;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author si.chen
 * @date 2020/3/27 15:10
 */
public interface OrganizationService {
    /**
     * 查询单个
     *
     * @param id id
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    OrganizationDTO get(String id) throws BaseException;

    /**
     * 查询单个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Organization find(Organization entity) throws BaseException;

    /**
     * 查询多个
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    List<OrganizationDTO> list(Organization entity, User loginUser) throws BaseException;

    /**
     * 查询分页
     *
     * @param entity 实体信息
     * @return 实体信息
     */
    Page<Organization> page(Organization entity);

    /**
     * 新建
     *
     * @param entity 实体信息
     * @param parentId parentId
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Organization create(Organization entity, String parentId, String[] roles) throws BaseException;

    /**
     * 查看是否存在相同名称的组织
     * @param organizationDTO
     */
    void findByName(OrganizationDTO organizationDTO);

    /**
     * 修改
     *
     * @param entity 实体信息
     * @return 实体信息
     * @throws BaseException 基本异常
     */
    Organization update(Organization entity, String parentId, String[] roles) throws BaseException;

    /**
     * 删除
     *
     * @param id id
     * @throws BaseException 基本异常
     */
    void delete(String id) throws BaseException;

    /**
     * 组织列表
     *
     * @return List<Organization>
     */
    List<Organization> listOrganization();

    /**
     * 查看所有的组织节点列表
     *
     * @return List<Organization>
     */
    List<Organization> listOrganizationTrees();

    /**
     * 创建根节点
     *
     * @param organization organization
     * @return Organization
     */
    Organization createRoot(Organization organization);

    /**
     * 增加子节点
     * @param parent 父
     * @param son 子
     * @return  Organization
     */
    Organization addChild(Organization parent, Organization son);

    /**
     * 增加子节点
     *
     * @param parentId parentId
     * @param son 子
     * @return  Organization
     */
    Organization addChild(String parentId, Organization son);

    /**
     * 删除节点
     *
     * @param organization organization
     * @throws BaseException BaseException
     */
    void delete(Organization organization) throws BaseException;

    /**
     * 删除节点
     *
     * @param id id
     */
    void deleteById(String id);

    /**
     * 获取父节点
     *
     * @param id id
     * @return Organization
     */
    Organization getParent(String id);

    /**
     * 获取下一层的子节点
     *
     * @param id id
     * @return List<Organization>
     */
    List<Organization> getChildren(String id);

    /**
     * 获取所有的祖先节点
     *
     * @param id id
     * @return Organization
     */
    List<Organization> getAncestors(String id);

    /**
     * 获取所有的后台节点
     *
     * @param id id
     * @param deep 层级
     * @return List<Organization>
     */
    List<Organization> getDescendants(String id, int deep);
}
