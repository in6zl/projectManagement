package com.pactera.esc.core.dao;

import com.pactera.esc.core.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author si.chen
 * @date 2020/3/26 16:53
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, String>, JpaSpecificationExecutor<Resource> {

    List<Resource> findByParentId(String parentId);

    @Query(value = "select distinct parent_id from sys_resource order by parent_id",nativeQuery = true)
    List<String> getParentIds();
}
