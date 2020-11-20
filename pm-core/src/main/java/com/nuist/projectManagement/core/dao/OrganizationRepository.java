package com.pactera.esc.core.dao;

import com.pactera.esc.core.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author si.chen
 * @date 2020/3/26 16:49
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String>, JpaSpecificationExecutor<Organization> {
    /**
     * 查找最大的rootId
     *
     * @return currentMaxId
     */
    @Query(value = "SELECT max(root_id) FROM sys_organization", nativeQuery = true)
    Integer findMaxRootId();

    Optional<Organization> findByName(String name);

    Optional<Organization> findBySupplierCode(String code);

    @Modifying
    @Query(value = "insert into sys_organization_role values(?1,?2)",nativeQuery = true)
    void addOrganization(String orgId, String roleId);

    @Modifying
    @Query(value = "delete from sys_organization_role where organization_id=?1",nativeQuery = true)
    void deleteOrganization(String id);
}
