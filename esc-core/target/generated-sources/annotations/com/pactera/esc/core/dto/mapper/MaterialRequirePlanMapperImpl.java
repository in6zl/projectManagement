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
public class MaterialRequirePlanMapperImpl implements MaterialRequirePlanMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public MaterialRequirePlan toEntity(MaterialRequirePlanDTO d) {
        if ( d == null ) {
            return null;
        }

        MaterialRequirePlan materialRequirePlan = new MaterialRequirePlan();

        if ( d.getId() != null ) {
            materialRequirePlan.setId( d.getId() );
        }
        if ( d.getState() != null ) {
            materialRequirePlan.setState( d.getState() );
        }
        if ( d.getWorkShop() != null ) {
            materialRequirePlan.setWorkShop( workShopDTOToWorkShop( d.getWorkShop() ) );
        }
        if ( d.getWarehouse() != null ) {
            materialRequirePlan.setWarehouse( warehouseDTOToWarehouse( d.getWarehouse() ) );
        }
        if ( d.getMaterial() != null ) {
            materialRequirePlan.setMaterial( productMaterialDTOToMaterial( d.getMaterial() ) );
        }
        if ( d.getNum() != null ) {
            materialRequirePlan.setNum( d.getNum() );
        }
        if ( d.getOutNum() != null ) {
            materialRequirePlan.setOutNum( d.getOutNum() );
        }
        if ( d.getRequireTime() != null ) {
            materialRequirePlan.setRequireTime( dateMapperUtil.stringToLocalDateTime( d.getRequireTime() ) );
        }

        return materialRequirePlan;
    }

    @Override
    public MaterialRequirePlanDTO toDTO(MaterialRequirePlan materialRequirePlan) {
        if ( materialRequirePlan == null ) {
            return null;
        }

        MaterialRequirePlanDTO materialRequirePlanDTO = new MaterialRequirePlanDTO();

        if ( materialRequirePlan.getMaterial() != null ) {
            materialRequirePlanDTO.setMaterial( materialToProductMaterialDTO( materialRequirePlan.getMaterial() ) );
        }
        if ( materialRequirePlan.getWorkShop() != null ) {
            materialRequirePlanDTO.setWorkShop( workShopToWorkShopDTO( materialRequirePlan.getWorkShop() ) );
        }
        if ( materialRequirePlan.getWarehouse() != null ) {
            materialRequirePlanDTO.setWarehouse( warehouseToWarehouseDTO( materialRequirePlan.getWarehouse() ) );
        }
        if ( materialRequirePlan.getId() != null ) {
            materialRequirePlanDTO.setId( materialRequirePlan.getId() );
        }
        if ( materialRequirePlan.getNum() != null ) {
            materialRequirePlanDTO.setNum( materialRequirePlan.getNum() );
        }
        if ( materialRequirePlan.getOutNum() != null ) {
            materialRequirePlanDTO.setOutNum( materialRequirePlan.getOutNum() );
        }
        if ( materialRequirePlan.getRequireTime() != null ) {
            materialRequirePlanDTO.setRequireTime( dateMapperUtil.localDateTimeToString( materialRequirePlan.getRequireTime() ) );
        }
        if ( materialRequirePlan.getState() != null ) {
            materialRequirePlanDTO.setState( materialRequirePlan.getState() );
        }

        return materialRequirePlanDTO;
    }

    protected WorkShop workShopDTOToWorkShop(WorkShopDTO workShopDTO) {
        if ( workShopDTO == null ) {
            return null;
        }

        WorkShop workShop = new WorkShop();

        if ( workShopDTO.getId() != null ) {
            workShop.setId( workShopDTO.getId() );
        }
        if ( workShopDTO.getName() != null ) {
            workShop.setName( workShopDTO.getName() );
        }
        if ( workShopDTO.getAddress() != null ) {
            workShop.setAddress( workShopDTO.getAddress() );
        }
        if ( workShopDTO.getManager() != null ) {
            workShop.setManager( workShopDTO.getManager() );
        }
        if ( workShopDTO.getPhone() != null ) {
            workShop.setPhone( workShopDTO.getPhone() );
        }

        return workShop;
    }

    protected Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( warehouseDTO.getCreator() != null ) {
            warehouse.setCreator( warehouseDTO.getCreator() );
        }
        if ( warehouseDTO.getOperator() != null ) {
            warehouse.setOperator( warehouseDTO.getOperator() );
        }
        if ( warehouseDTO.getCreateTime() != null ) {
            warehouse.setCreateTime( warehouseDTO.getCreateTime() );
        }
        if ( warehouseDTO.getUpdateTime() != null ) {
            warehouse.setUpdateTime( warehouseDTO.getUpdateTime() );
        }
        if ( warehouseDTO.getId() != null ) {
            warehouse.setId( warehouseDTO.getId() );
        }
        if ( warehouseDTO.getName() != null ) {
            warehouse.setName( warehouseDTO.getName() );
        }
        if ( warehouseDTO.getArea() != null ) {
            warehouse.setArea( warehouseDTO.getArea() );
        }
        if ( warehouseDTO.getAddress() != null ) {
            warehouse.setAddress( warehouseDTO.getAddress() );
        }
        if ( warehouseDTO.getLongitude() != null ) {
            warehouse.setLongitude( warehouseDTO.getLongitude() );
        }
        if ( warehouseDTO.getLatitude() != null ) {
            warehouse.setLatitude( warehouseDTO.getLatitude() );
        }
        if ( warehouseDTO.getType() != null ) {
            warehouse.setType( warehouseDTO.getType() );
        }
        if ( warehouseDTO.getIsFull() != null ) {
            warehouse.setIsFull( warehouseDTO.getIsFull() );
        }
        if ( warehouseDTO.getIsOpen() != null ) {
            warehouse.setIsOpen( warehouseDTO.getIsOpen() );
        }
        if ( warehouseDTO.getPageable() != null ) {
            warehouse.setPageable( warehouseDTO.getPageable() );
        }

        return warehouse;
    }

    protected Material productMaterialDTOToMaterial(ProductMaterialDTO productMaterialDTO) {
        if ( productMaterialDTO == null ) {
            return null;
        }

        Material material = new Material();

        if ( productMaterialDTO.getId() != null ) {
            material.setId( productMaterialDTO.getId() );
        }
        if ( productMaterialDTO.getCode() != null ) {
            material.setCode( productMaterialDTO.getCode() );
        }
        if ( productMaterialDTO.getName() != null ) {
            material.setName( productMaterialDTO.getName() );
        }

        return material;
    }

    protected ProductMaterialDTO materialToProductMaterialDTO(Material material) {
        if ( material == null ) {
            return null;
        }

        ProductMaterialDTO productMaterialDTO = new ProductMaterialDTO();

        if ( material.getUnitName() != null ) {
            productMaterialDTO.setUnit( material.getUnitName() );
        }
        if ( material.getId() != null ) {
            productMaterialDTO.setId( material.getId() );
        }
        if ( material.getName() != null ) {
            productMaterialDTO.setName( material.getName() );
        }
        if ( material.getCode() != null ) {
            productMaterialDTO.setCode( material.getCode() );
        }

        return productMaterialDTO;
    }

    protected WorkShopDTO workShopToWorkShopDTO(WorkShop workShop) {
        if ( workShop == null ) {
            return null;
        }

        WorkShopDTO workShopDTO = new WorkShopDTO();

        if ( workShop.getId() != null ) {
            workShopDTO.setId( workShop.getId() );
        }
        if ( workShop.getName() != null ) {
            workShopDTO.setName( workShop.getName() );
        }
        if ( workShop.getAddress() != null ) {
            workShopDTO.setAddress( workShop.getAddress() );
        }
        if ( workShop.getManager() != null ) {
            workShopDTO.setManager( workShop.getManager() );
        }
        if ( workShop.getPhone() != null ) {
            workShopDTO.setPhone( workShop.getPhone() );
        }

        return workShopDTO;
    }

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        if ( warehouse.getId() != null ) {
            warehouseDTO.setId( warehouse.getId() );
        }
        if ( warehouse.getName() != null ) {
            warehouseDTO.setName( warehouse.getName() );
        }
        if ( warehouse.getArea() != null ) {
            warehouseDTO.setArea( warehouse.getArea() );
        }
        if ( warehouse.getAddress() != null ) {
            warehouseDTO.setAddress( warehouse.getAddress() );
        }
        if ( warehouse.getLongitude() != null ) {
            warehouseDTO.setLongitude( warehouse.getLongitude() );
        }
        if ( warehouse.getLatitude() != null ) {
            warehouseDTO.setLatitude( warehouse.getLatitude() );
        }
        if ( warehouse.getType() != null ) {
            warehouseDTO.setType( warehouse.getType() );
        }
        if ( warehouse.getIsFull() != null ) {
            warehouseDTO.setIsFull( warehouse.getIsFull() );
        }
        if ( warehouse.getIsOpen() != null ) {
            warehouseDTO.setIsOpen( warehouse.getIsOpen() );
        }
        if ( warehouse.getCreator() != null ) {
            warehouseDTO.setCreator( warehouse.getCreator() );
        }
        if ( warehouse.getOperator() != null ) {
            warehouseDTO.setOperator( warehouse.getOperator() );
        }
        if ( warehouse.getCreateTime() != null ) {
            warehouseDTO.setCreateTime( warehouse.getCreateTime() );
        }
        if ( warehouse.getUpdateTime() != null ) {
            warehouseDTO.setUpdateTime( warehouse.getUpdateTime() );
        }
        if ( warehouse.getPageable() != null ) {
            warehouseDTO.setPageable( warehouse.getPageable() );
        }

        return warehouseDTO;
    }
}
