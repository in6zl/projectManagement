package com.pactera.esc.core.dao;

import com.pactera.esc.core.domain.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSettingRepository extends JpaRepository<SystemSetting,String>,
    JpaSpecificationExecutor<SystemSetting> {
}
