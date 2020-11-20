package com.pactera.esc.core.dao;

import com.pactera.esc.core.domain.DictType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author 陸文
 * @title: sysDictTypeRepository
 * @projectName end-supply-chain
 * @date 2020/3/2610:33
 */
@Repository
public interface DictTypeRepository extends JpaRepository<DictType,String>, JpaSpecificationExecutor<DictType> {

    Optional<DictType> findByName(String name);

    Optional<DictType> findByNameAndIdNot(String name, String id);
}
