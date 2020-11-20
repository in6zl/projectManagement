package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.vo.OrganizationVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-20T13:51:34+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class OrganizationMapperImpl implements OrganizationMapper {

    @Override
    public OrganizationDTO toDTO(Organization entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setRoles( dto( entity.getRoles() ) );
        organizationDTO.setId( entity.getId() );
        organizationDTO.setName( entity.getName() );
        organizationDTO.setType( entity.getType() );
        organizationDTO.setAddress( entity.getAddress() );
        organizationDTO.setPhone( entity.getPhone() );
        organizationDTO.setSupplierCode( entity.getSupplierCode() );

        return organizationDTO;
    }

    @Override
    public Organization toEntity(OrganizationDTO organizationDTO) {
        if ( organizationDTO == null ) {
            return null;
        }

        Organization organization = new Organization();

        organization.setId( organizationDTO.getId() );
        organization.setName( organizationDTO.getName() );
        organization.setType( organizationDTO.getType() );
        organization.setAddress( organizationDTO.getAddress() );
        organization.setPhone( organizationDTO.getPhone() );
        organization.setSupplierCode( organizationDTO.getSupplierCode() );

        return organization;
    }

    @Override
    public OrganizationVO toVO(Organization organization) {
        if ( organization == null ) {
            return null;
        }

        OrganizationVO organizationVO = new OrganizationVO();

        organizationVO.setId( organization.getId() );
        organizationVO.setName( organization.getName() );
        organizationVO.setAddress( organization.getAddress() );
        organizationVO.setPhone( organization.getPhone() );
        organizationVO.setSupplierCode( organization.getSupplierCode() );
        organizationVO.setCreateTime( organization.getCreateTime() );

        return organizationVO;
    }
}
