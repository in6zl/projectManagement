package com.pactera.esc.config.customer;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.pactera.esc.factory.service.MaterialRequirePlanService;
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
public class OutboundOrderMaterialCustomer {

    private MaterialRequirePlanService materialRequirePlanService;


    @Subscribe
    @AllowConcurrentEvents
    public void create(OutboundOrderMaterial outboundOrderMaterial) {
        log.info("出库物料完成物料需求计划");
        materialRequirePlanService.compilePlan(outboundOrderMaterial);

    }
}
