package com.pactera.esc.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pactera.esc.core.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8892152703495958584L;

    @CreatedBy
    private String creator;

    @LastModifiedBy
    private String operator;

    @CreatedDate
    @Column(name = "create_time")
    @JsonFormat(pattern = DateUtil.DATE_FORMAT_DEFAULT)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @JsonFormat(pattern = DateUtil.DATE_FORMAT_DEFAULT)
    private LocalDateTime updateTime;
}
