package com.pactera.esc.controller;

import com.pactera.esc.controller.api.ResourceApi;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.sys.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资源控制器
 *
 * @author caimh
 * @date 2020/03/24
 */
@RequestMapping("api/resource")
@RestController
@AllArgsConstructor
public class ResourceController implements ResourceApi {

    private final ResourceService resourceService;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<JsonResult> detail(@PathVariable("id") String id) throws BaseException {
        ResourceDTO res = resourceService.detail(id);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("find")
    public ResponseEntity<JsonResult> find(@RequestBody Resource resource) throws BaseException {
        ResourceDTO res = resourceService.find(resource);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("list")
    public ResponseEntity<JsonResult> list(@RequestBody Resource resource) throws BaseException {
        List<ResourceDTO> res = resourceService.list(resource);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("page")
    @PreAuthorize("hasAuthority('resourceList')")
    public ResponseEntity<JsonResult> page(@RequestBody Resource resource) throws BaseException {
        Page<ResourceDTO> res = resourceService.page(resource);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("create")
    @PreAuthorize("hasAuthority('resource:add')")
    public ResponseEntity<JsonResult> create(@Validated
                                             @RequestBody Resource resource) throws BaseException {
        return ResponseEntity.ok(JsonResult.ok(resourceService.create(resource)));
    }

    @Override
    @PutMapping("update")
    @PreAuthorize("hasAuthority('resource:edit')")
    public ResponseEntity<JsonResult> update(@Validated({Constants.CheckId.class})
                                             @RequestBody Resource resource) throws BaseException {
        resourceService.update(resource);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('resource:delete')")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") String id) throws BaseException {
        resourceService.delete(id);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    public ResponseEntity<JsonResult> getResourceByParentId(String parentId) {
        return ResponseEntity.ok(JsonResult.ok(resourceService.getResourceByParentId(parentId)));
    }

    @Override
    public ResponseEntity<JsonResult> getSecondLevelResource() {
        return ResponseEntity.ok(JsonResult.ok(resourceService.getSecondLevelResource()));
    }

    //------------------角色-资源-----------------------

//    @Override
//    @PostMapping("pageRoleResource")
//    public ResponseEntity<JsonResult> pageRoleResource(@RequestBody RoleResource roleResource) throws BaseException {
//        Page<RoleResourceDTO> res = roleResourceService.page(roleResource);
//        return ResponseEntity.ok(JsonResult.ok(res));
//    }
//
//    @Override
//    @PostMapping("createRoleResource")
//    public ResponseEntity<JsonResult> createRoleResource(@Validated
//                                                         @RequestBody RoleResource roleResource) throws BaseException {
//        roleResourceService.create(roleResource);
//        return ResponseEntity.ok(JsonResult.ok());
//    }
//
//    @Override
//    @DeleteMapping("deleteRoleResource")
//    public ResponseEntity<JsonResult> deleteRoleResource(@RequestBody RoleResource roleResource) throws BaseException {
//        roleResourceService.deleteByEntity(roleResource);
//        return ResponseEntity.ok(JsonResult.ok());
//    }
}
