package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.util.DateMapperUtil;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ProductionRecordMapperImpl implements ProductionRecordMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public ProductionRecord toEntity(ProductionRecordDTO d) {
        if ( d == null ) {
            return null;
        }

        ProductionRecord productionRecord = new ProductionRecord();

        productionRecord.setCreateTime( dateMapperUtil.stringToLocalDateTime( d.getCreateTime() ) );
        productionRecord.setId( d.getId() );
        if ( d.getNum() != null ) {
            productionRecord.setNum( d.getNum() );
        }
        productionRecord.setBatch( d.getBatch() );
        productionRecord.setProductionDate( dateMapperUtil.stringToLocalDateTime( d.getProductionDate() ) );
        productionRecord.setStatus( d.getStatus() );

        return productionRecord;
    }

    @Override
    public ProductionRecordDTO toDTO(ProductionRecord productionRecord) {
        if ( productionRecord == null ) {
            return null;
        }

        ProductionRecordDTO productionRecordDTO = new ProductionRecordDTO();

        productionRecordDTO.setPlanId( productionRecordProductionPlanId( productionRecord ) );
        productionRecordDTO.setProductId( productionRecordProductId( productionRecord ) );
        productionRecordDTO.setProductName( productionRecordProductName( productionRecord ) );
        productionRecordDTO.setPlanCode( productionRecordProductionPlanCode( productionRecord ) );
        productionRecordDTO.setId( productionRecord.getId() );
        productionRecordDTO.setNum( productionRecord.getNum() );
        productionRecordDTO.setBatch( productionRecord.getBatch() );
        productionRecordDTO.setCreateTime( dateMapperUtil.localDateTimeToString( productionRecord.getCreateTime() ) );
        productionRecordDTO.setProductionDate( dateMapperUtil.localDateTimeToString( productionRecord.getProductionDate() ) );
        productionRecordDTO.setStatus( productionRecord.getStatus() );

        return productionRecordDTO;
    }

    private String productionRecordProductionPlanId(ProductionRecord productionRecord) {
        if ( productionRecord == null ) {
            return null;
        }
        ProductionPlan productionPlan = productionRecord.getProductionPlan();
        if ( productionPlan == null ) {
            return null;
        }
        String id = productionPlan.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productionRecordProductId(ProductionRecord productionRecord) {
        if ( productionRecord == null ) {
            return null;
        }
        Product product = productionRecord.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productionRecordProductName(ProductionRecord productionRecord) {
        if ( productionRecord == null ) {
            return null;
        }
        Product product = productionRecord.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String productionRecordProductionPlanCode(ProductionRecord productionRecord) {
        if ( productionRecord == null ) {
            return null;
        }
        ProductionPlan productionPlan = productionRecord.getProductionPlan();
        if ( productionPlan == null ) {
            return null;
        }
        String code = productionPlan.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }
}
