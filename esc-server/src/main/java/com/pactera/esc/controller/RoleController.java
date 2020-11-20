package com.pactera.esc.controller;

import com.pactera.esc.config.security.SecurityUtils;
import com.pactera.esc.controller.api.RoleApi;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.dto.mapper.RoleMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.vo.RoleVO;
import com.pactera.esc.sys.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author caimh
 * @date 2020/03/24
 */
@RequestMapping("api/role")
@RestController
@AllArgsConstructor
public class RoleController implements RoleApi {

    private final RoleService roleService;
    private final RoleMapper roleMapper;
    private final SecurityUtils securityUtils;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<JsonResult> get(@PathVariable("id") String id) throws BaseException {
        Role res = roleService.get(id);
        return ResponseEntity.ok(JsonResult.ok(roleMapper.toDTO(res)));
    }

    @Override
    @PostMapping("find")
    public ResponseEntity<JsonResult> find(@RequestBody Role role) throws BaseException {
        Role res = roleService.find(role);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("list")
    public ResponseEntity<JsonResult> list(@RequestBody Role role) throws BaseException {
        List<Role> res = roleService.list(role);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("page")
    @PreAuthorize("hasAuthority('role:list')")
    public ResponseEntity<JsonResult> page(@RequestBody Role role) throws BaseException {
        Page<RoleVO> res = roleService.page(role).map(roleMapper::toVO);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("create")
    @PreAuthorize("hasAuthority('role:add')")
    public ResponseEntity<JsonResult> create(@RequestBody RoleDTO roleDTO) throws BaseException {
        roleService.create(roleDTO);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @PutMapping("update")
    @PreAuthorize("hasAuthority('role:edit')")
    public ResponseEntity<JsonResult> update(RoleDTO roleDTO) throws BaseException {
        roleService.update(roleDTO);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") String id) throws BaseException {
        roleService.delete(id);
        return ResponseEntity.ok(JsonResult.ok());
    }

    //-------------------用户-角色---------------------

//    @Override
//    @PostMapping("pageUserRole")
//    public ResponseEntity<JsonResult> pageUserRole(@RequestBody UserRole userRole) throws BaseException {
//        Page<UserRoleDTO> res = userRoleService.page(userRole);
//        return ResponseEntity.ok(JsonResult.ok(res));
//    }

//    @Override
//    @PostMapping("createUserRole")
//    public ResponseEntity<JsonResult> createUserRole(@Validated
//                                                     @RequestBody UserRole userRole) throws BaseException {
//        userRoleService.create(userRole);
//        return ResponseEntity.ok(JsonResult.ok());
//    }

//    @Override
//    @DeleteMapping("deleteUserRole")
//    public ResponseEntity<JsonResult> deleteUserRole(@RequestBody UserRole userRole) throws BaseException {
//        userRoleService.deleteByEntity(userRole);
//        return ResponseEntity.ok(JsonResult.ok());
//    }

    //----------------组织-角色------------------

    /*@Override
    @PostMapping("pageOrganizationRole")
    public ResponseEntity<JsonResult> pageOrganizationRole(@RequestBody OrganizationRole organizationRole) throws BaseException {
        Page<OrganizationRoleDTO> res = organizationRoleService.page(organizationRole);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("createOrganizationRole")
    public ResponseEntity<JsonResult> createOrganizationRole(@Validated
                                                             @RequestBody OrganizationRole organizationRole) throws BaseException {
        organizationRoleService.create(organizationRole);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @DeleteMapping("deleteOrganizationRole")
    public ResponseEntity<JsonResult> deleteOrganizationRole(@RequestBody OrganizationRole organizationRole) throws BaseException {
        organizationRoleService.deleteByEntity(organizationRole);
        return ResponseEntity.ok(JsonResult.ok());
    }*/

    @Override
    public JsonResult<List<ResourceDTO>> getResourceTree() throws BaseException {
        User loginUser = securityUtils.getLoginUser();
        return JsonResult.ok(roleService.getResourceTree(loginUser));
    }

    @Override
    public ResponseEntity<JsonResult> getRolesExclusionAdmin() throws BaseException {
        return ResponseEntity.ok(JsonResult.ok(roleService.getRolesExclusionAdmin()));
    }

    @Override
    public ResponseEntity<JsonResult> getRolesByOrg(String organizationId) throws BaseException {
        return ResponseEntity.ok(JsonResult.ok(roleService.getRolesByOrg(organizationId)));
    }
}
