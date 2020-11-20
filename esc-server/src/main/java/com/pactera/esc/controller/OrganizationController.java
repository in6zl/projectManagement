package com.pactera.esc.controller;

import com.pactera.esc.config.security.SecurityUtils;
import com.pactera.esc.controller.api.OrganizationApi;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.dto.mapper.OrganizationMapper;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.vo.OrganizationVO;
import com.pactera.esc.sys.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 组织
 *
 * @author caimh
 * @date 2020/03/30
 */
@RestController
@AllArgsConstructor
public class OrganizationController implements OrganizationApi {
    private final OrganizationService organizationService;
    private final SecurityUtils securityUtils;
    private final OrganizationMapper organizationMapper;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<JsonResult> get(@PathVariable("id") String id) throws BaseException {
        return ResponseEntity.ok(JsonResult.ok(organizationService.get(id)));
    }

    @Override
    @PostMapping("find")
    public ResponseEntity<JsonResult> find(@RequestBody Organization organization) throws BaseException {
        Organization res = organizationService.find(organization);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("list")
    public ResponseEntity<JsonResult> list(@RequestBody Organization organization) throws BaseException {
        User loginUser = securityUtils.getLoginUser();
        List<OrganizationDTO> res = organizationService.list(organization, loginUser);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("page")
    @PreAuthorize("hasAuthority('organization:list')")
    public ResponseEntity<JsonResult> page(@RequestBody Organization organization) throws BaseException {
        Page<OrganizationVO> res = organizationService.page(organization)
            .map(organizationMapper::toVO);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PreAuthorize("hasAuthority('organization:add')")
    public JsonResult create(SaveOrganizationDTO organization) throws BaseException {
        return JsonResult.ok(organizationService.create(organization.getOrganization(), organization.getParentId(),
            organization.getRoles()));

    }

    @Override
    @PreAuthorize("hasAuthority('organization')")
    public JsonResult update(SaveOrganizationDTO organization) {
        return JsonResult.ok(organizationService.update(organization.getOrganization(), organization.getParentId(),
            organization.getRoles()));
    }

    @Override
    @PreAuthorize("hasAuthority('organization:delete')")
    public ResponseEntity<JsonResult> delete(String organizationId) {
        organizationService.delete(organizationId);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    public ResponseEntity<JsonResult> findByName(OrganizationDTO organizationDTO) throws BaseException {
        try {
            organizationService.findByName(organizationDTO);
            return ResponseEntity.ok(JsonResult.ok());
        } catch (Exception e) {
            return ResponseEntity.ok(JsonResult.failed("01", "组织名称重复"));
        }
    }
}
