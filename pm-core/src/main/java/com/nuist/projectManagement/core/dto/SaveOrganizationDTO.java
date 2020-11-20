package com.pactera.esc.core.dto;

import com.pactera.esc.core.domain.Organization;
import lombok.Data;

import java.io.Serializable;

/**
 * @author minhao.cai
 * @date 2020/3/30
 */
@Data
public class SaveOrganizationDTO implements Serializable {

    private static final long serialVersionUID = 8548561556349512656L;

    private Organization organization;

    private String parentId;

    private String[] roles;

}
