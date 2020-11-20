package com.pactera.esc.config.customer;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.pactera.esc.core.dto.message.MaterialRequireMessage;
import com.pactera.esc.factory.service.MaterialTransportPlanService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zmj
 * @description
 * @date 2020/5/25 0025
 */
@Component
@Slf4j
@AllArgsConstructor
public class MaterialRequireMessageCustomer {

    private MaterialTransportPlanService materialTransportPlanService;


    @Subscribe
    @AllowConcurrentEvents
    public void create(MaterialRequireMessage materialRequireMessage) {
        log.info("判断是否需要生成调货计划");
        materialTransportPlanService.createMaterialTransportPlan(materialRequireMessage);

    }
}
