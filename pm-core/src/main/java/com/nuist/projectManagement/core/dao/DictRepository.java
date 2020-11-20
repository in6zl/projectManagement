package com.pactera.esc.core.dao;

import com.pactera.esc.core.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author 陸文
 * @title: DictRepository
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2713:40
 */
@Repository
public interface DictRepository  extends JpaRepository<Dict,String>, JpaSpecificationExecutor<Dict> {

    Optional<Dict> findByDictKey(String key);

    Optional<Dict> findByDictKeyAndIdNot(String key, String id);

    List<Dict> findByDictType_Id(String id);
    Optional<Dict> findByDictType_IdAndDictValue(String dictTypeId, String dicValue);

    @Query(value = "select d.* " +
        "from sys_dict d " +
        "LEFT JOIN sys_dict_type dt " +
        "on d.dict_type_id=dt.id " +
        "WHERE dt.`name`=?1",nativeQuery = true)
    List<Dict> findByDictTypeName(String id);
}
