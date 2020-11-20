package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.dto.security.UserDetailsDTO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setOrganizationId( userOrganizationId( user ) );
        userDTO.setSupplierName( userSupplierName( user ) );
        userDTO.setSupplierId( userSupplierId( user ) );
        userDTO.setOrganizationName( userOrganizationName( user ) );
        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setRealName( user.getRealName() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPhone( user.getPhone() );
        userDTO.setType( user.getType() );
        userDTO.setAddress( user.getAddress() );
        userDTO.setImagePath( user.getImagePath() );
        userDTO.setEnable( user.getEnable() );
        userDTO.setLastLogin( user.getLastLogin() );
        userDTO.setRoles( roleListToRoleDTOSet( user.getRoles() ) );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setOrganization( userDTOToOrganization( userDTO ) );
        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setRealName( userDTO.getRealName() );
        user.setPassword( userDTO.getPassword() );
        user.setEmail( userDTO.getEmail() );
        user.setPhone( userDTO.getPhone() );
        user.setType( userDTO.getType() );
        user.setAddress( userDTO.getAddress() );
        user.setImagePath( userDTO.getImagePath() );
        user.setEnable( userDTO.getEnable() );
        user.setLastLogin( userDTO.getLastLogin() );
        user.setRoles( roleDTOSetToRoleList( userDTO.getRoles() ) );

        return user;
    }

    @Override
    public UserDetailsDTO toUserDetails(User user, Boolean isAccountNonLocked) {
        if ( user == null && isAccountNonLocked == null ) {
            return null;
        }

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

        if ( user != null ) {
            userDetailsDTO.setPassword( user.getPassword() );
            userDetailsDTO.setAuthorities( rolesToAuthorities( user.getRoles() ) );
            userDetailsDTO.setUsername( user.getUsername() );
            userDetailsDTO.setId( user.getId() );
        }
        if ( isAccountNonLocked != null ) {
            userDetailsDTO.setIsAccountNonLocked( isAccountNonLocked );
        }

        return userDetailsDTO;
    }

    private String userOrganizationId(User user) {
        if ( user == null ) {
            return null;
        }
        Organization organization = user.getOrganization();
        if ( organization == null ) {
            return null;
        }
        String id = organization.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userSupplierName(User user) {
        if ( user == null ) {
            return null;
        }
        Supplier supplier = user.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String name = supplier.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String userSupplierId(User user) {
        if ( user == null ) {
            return null;
        }
        Supplier supplier = user.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String id = supplier.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userOrganizationName(User user) {
        if ( user == null ) {
            return null;
        }
        Organization organization = user.getOrganization();
        if ( organization == null ) {
            return null;
        }
        String name = organization.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected ResourceDTO resourceToResourceDTO(Resource resource) {
        if ( resource == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resource.getId() );
        resourceDTO.setName( resource.getName() );
        if ( resource.getType() != null ) {
            resourceDTO.setType( resource.getType().byteValue() );
        }
        resourceDTO.setUrl( resource.getUrl() );
        resourceDTO.setPermission( resource.getPermission() );
        resourceDTO.setIcon( resource.getIcon() );
        resourceDTO.setLevel( resource.getLevel() );
        resourceDTO.setSort( resource.getSort() );
        resourceDTO.setParentId( resource.getParentId() );
        resourceDTO.setTitle( resource.getTitle() );

        return resourceDTO;
    }

    protected List<ResourceDTO> resourceListToResourceDTOList(List<Resource> list) {
        if ( list == null ) {
            return null;
        }

        List<ResourceDTO> list1 = new ArrayList<ResourceDTO>( list.size() );
        for ( Resource resource : list ) {
            list1.add( resourceToResourceDTO( resource ) );
        }

        return list1;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setName( role.getName() );
        roleDTO.setDescription( role.getDescription() );
        roleDTO.setResources( resourceListToResourceDTOList( role.getResources() ) );

        return roleDTO;
    }

    protected Set<RoleDTO> roleListToRoleDTOSet(List<Role> list) {
        if ( list == null ) {
            return null;
        }

        Set<RoleDTO> set = new HashSet<RoleDTO>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Role role : list ) {
            set.add( roleToRoleDTO( role ) );
        }

        return set;
    }

    protected Organization userDTOToOrganization(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        Organization organization = new Organization();

        organization.setName( userDTO.getOrganizationName() );
        organization.setId( userDTO.getOrganizationId() );

        return organization;
    }

    protected Resource resourceDTOToResource(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( resourceDTO.getId() );
        resource.setName( resourceDTO.getName() );
        if ( resourceDTO.getType() != null ) {
            resource.setType( resourceDTO.getType().intValue() );
        }
        resource.setUrl( resourceDTO.getUrl() );
        resource.setPermission( resourceDTO.getPermission() );
        resource.setLevel( resourceDTO.getLevel() );
        resource.setSort( resourceDTO.getSort() );
        resource.setParentId( resourceDTO.getParentId() );
        resource.setIcon( resourceDTO.getIcon() );
        resource.setTitle( resourceDTO.getTitle() );

        return resource;
    }

    protected List<Resource> resourceDTOListToResourceList(List<ResourceDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Resource> list1 = new ArrayList<Resource>( list.size() );
        for ( ResourceDTO resourceDTO : list ) {
            list1.add( resourceDTOToResource( resourceDTO ) );
        }

        return list1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setName( roleDTO.getName() );
        role.setDescription( roleDTO.getDescription() );
        role.setResources( resourceDTOListToResourceList( roleDTO.getResources() ) );

        return role;
    }

    protected List<Role> roleDTOSetToRoleList(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( set.size() );
        for ( RoleDTO roleDTO : set ) {
            list.add( roleDTOToRole( roleDTO ) );
        }

        return list;
    }
}
