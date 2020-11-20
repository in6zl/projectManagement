package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:52+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ProductionPlanMapperImpl implements ProductionPlanMapper {

    @Override
    public ProductionPlan toEntity(ProductionPlanDTO d) {
        if ( d == null ) {
            return null;
        }

        ProductionPlan productionPlan = new ProductionPlan();

        if ( d.getId() != null ) {
            productionPlan.setId( d.getId() );
        }
        if ( d.getCode() != null ) {
            productionPlan.setCode( d.getCode() );
        }
        if ( d.getProduct() != null ) {
            productionPlan.setProduct( d.getProduct() );
        }
        if ( d.getNum() != null ) {
            productionPlan.setNum( d.getNum() );
        }
        if ( d.getProductionLine() != null ) {
            productionPlan.setProductionLine( productLineVOToProductionLine( d.getProductionLine() ) );
        }
        if ( d.getStartDate() != null ) {
            productionPlan.setStartDate( d.getStartDate() );
        }
        if ( d.getEndDate() != null ) {
            productionPlan.setEndDate( d.getEndDate() );
        }
        if ( d.getStatus() != null ) {
            productionPlan.setStatus( d.getStatus() );
        }

        return productionPlan;
    }

    @Override
    public ProductionPlanDTO toDTO(ProductionPlan entity) {
        if ( entity == null ) {
            return null;
        }

        ProductionPlanDTO productionPlanDTO = new ProductionPlanDTO();

        if ( entity.getProductionLine() != null ) {
            productionPlanDTO.setProductionLine( productionLineToProductLineVO( entity.getProductionLine() ) );
        }
        if ( entity.getId() != null ) {
            productionPlanDTO.setId( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            productionPlanDTO.setCode( entity.getCode() );
        }
        if ( entity.getProduct() != null ) {
            productionPlanDTO.setProduct( entity.getProduct() );
        }
        if ( entity.getNum() != null ) {
            productionPlanDTO.setNum( entity.getNum() );
        }
        if ( entity.getStartDate() != null ) {
            productionPlanDTO.setStartDate( entity.getStartDate() );
        }
        if ( entity.getEndDate() != null ) {
            productionPlanDTO.setEndDate( entity.getEndDate() );
        }
        if ( entity.getStatus() != null ) {
            productionPlanDTO.setStatus( entity.getStatus() );
        }

        return productionPlanDTO;
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

    protected ProductionLine productLineVOToProductionLine(ProductLineVO productLineVO) {
        if ( productLineVO == null ) {
            return null;
        }

        ProductionLine productionLine = new ProductionLine();

        if ( productLineVO.getId() != null ) {
            productionLine.setId( productLineVO.getId() );
        }
        if ( productLineVO.getName() != null ) {
            productionLine.setName( productLineVO.getName() );
        }
        if ( productLineVO.getAddress() != null ) {
            productionLine.setAddress( productLineVO.getAddress() );
        }
        if ( productLineVO.getLeader() != null ) {
            productionLine.setLeader( productLineVO.getLeader() );
        }
        if ( productLineVO.getWarehouse() != null ) {
            productionLine.setWarehouse( warehouseDTOToWarehouse( productLineVO.getWarehouse() ) );
        }
        if ( productLineVO.getLeaderPhone() != null ) {
            productionLine.setLeaderPhone( productLineVO.getLeaderPhone() );
        }
        if ( productLineVO.getChejian() != null ) {
            productionLine.setChejian( workShopDTOToWorkShop( productLineVO.getChejian() ) );
        }

        return productionLine;
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

    protected ProductLineVO productionLineToProductLineVO(ProductionLine productionLine) {
        if ( productionLine == null ) {
            return null;
        }

        ProductLineVO productLineVO = new ProductLineVO();

        if ( productionLine.getId() != null ) {
            productLineVO.setId( productionLine.getId() );
        }
        if ( productionLine.getName() != null ) {
            productLineVO.setName( productionLine.getName() );
        }
        if ( productionLine.getAddress() != null ) {
            productLineVO.setAddress( productionLine.getAddress() );
        }
        if ( productionLine.getLeader() != null ) {
            productLineVO.setLeader( productionLine.getLeader() );
        }
        if ( productionLine.getWarehouse() != null ) {
            productLineVO.setWarehouse( warehouseToWarehouseDTO( productionLine.getWarehouse() ) );
        }
        if ( productionLine.getLeaderPhone() != null ) {
            productLineVO.setLeaderPhone( productionLine.getLeaderPhone() );
        }
        if ( productionLine.getChejian() != null ) {
            productLineVO.setChejian( workShopToWorkShopDTO( productionLine.getChejian() ) );
        }

        return productLineVO;
    }
}
