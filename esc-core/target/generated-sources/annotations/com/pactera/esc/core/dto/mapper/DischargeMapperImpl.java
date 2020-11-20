package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class DischargeMapperImpl implements DischargeMapper {

    @Override
    public DischargeDTO toDTO(Discharge entity) {
        if ( entity == null ) {
            return null;
        }

        DischargeDTO dischargeDTO = new DischargeDTO();

        String name = entityMaterialName( entity );
        if ( name != null ) {
            dischargeDTO.setMaterialName( name );
        }
        String id = entityWarehouseId( entity );
        if ( id != null ) {
            dischargeDTO.setWarehouseId( id );
        }
        String id1 = entityInvoiceId( entity );
        if ( id1 != null ) {
            dischargeDTO.setSendOrderId( id1 );
        }
        String id2 = entityMaterialId( entity );
        if ( id2 != null ) {
            dischargeDTO.setMaterialId( id2 );
        }
        String code = entityInvoiceCode( entity );
        if ( code != null ) {
            dischargeDTO.setInvoiceCode( code );
        }
        String name1 = entityWarehouseName( entity );
        if ( name1 != null ) {
            dischargeDTO.setWarehouseName( name1 );
        }
        if ( entity.getId() != null ) {
            dischargeDTO.setId( entity.getId() );
        }
        if ( entity.getCreateTime() != null ) {
            dischargeDTO.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getNum() != null ) {
            dischargeDTO.setNum( entity.getNum() );
        }

        return dischargeDTO;
    }

    @Override
    public Discharge toEntity(DischargeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Discharge discharge = new Discharge();

        discharge.setWarehouse( dischargeDTOToWarehouse( dto ) );
        discharge.setInvoice( dischargeDTOToInvoice( dto ) );
        discharge.setMaterial( dischargeDTOToMaterial( dto ) );
        if ( dto.getCreateTime() != null ) {
            discharge.setCreateTime( dto.getCreateTime() );
        }
        if ( dto.getId() != null ) {
            discharge.setId( dto.getId() );
        }
        if ( dto.getNum() != null ) {
            discharge.setNum( dto.getNum() );
        }

        return discharge;
    }

    private String entityMaterialName(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Material material = discharge.getMaterial();
        if ( material == null ) {
            return null;
        }
        String name = material.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityWarehouseId(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Warehouse warehouse = discharge.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityInvoiceId(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Invoice invoice = discharge.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String id = invoice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityMaterialId(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Material material = discharge.getMaterial();
        if ( material == null ) {
            return null;
        }
        String id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityInvoiceCode(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Invoice invoice = discharge.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String code = invoice.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityWarehouseName(Discharge discharge) {
        if ( discharge == null ) {
            return null;
        }
        Warehouse warehouse = discharge.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Warehouse dischargeDTOToWarehouse(DischargeDTO dischargeDTO) {
        if ( dischargeDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( dischargeDTO.getWarehouseId() != null ) {
            warehouse.setId( dischargeDTO.getWarehouseId() );
        }
        if ( dischargeDTO.getWarehouseName() != null ) {
            warehouse.setName( dischargeDTO.getWarehouseName() );
        }

        return warehouse;
    }

    protected Invoice dischargeDTOToInvoice(DischargeDTO dischargeDTO) {
        if ( dischargeDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        if ( dischargeDTO.getInvoiceCode() != null ) {
            invoice.setCode( dischargeDTO.getInvoiceCode() );
        }
        if ( dischargeDTO.getSendOrderId() != null ) {
            invoice.setId( dischargeDTO.getSendOrderId() );
        }

        return invoice;
    }

    protected Material dischargeDTOToMaterial(DischargeDTO dischargeDTO) {
        if ( dischargeDTO == null ) {
            return null;
        }

        Material material = new Material();

        if ( dischargeDTO.getMaterialId() != null ) {
            material.setId( dischargeDTO.getMaterialId() );
        }
        if ( dischargeDTO.getMaterialName() != null ) {
            material.setName( dischargeDTO.getMaterialName() );
        }

        return material;
    }
}
