package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ProductLineMapperImpl implements ProductLineMapper {

    @Override
    public ProductionLine toEntity(ProductLineVO d) {
        if ( d == null ) {
            return null;
        }

        ProductionLine productionLine = new ProductionLine();

        if ( d.getId() != null ) {
            productionLine.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            productionLine.setName( d.getName() );
        }
        if ( d.getAddress() != null ) {
            productionLine.setAddress( d.getAddress() );
        }
        if ( d.getLeader() != null ) {
            productionLine.setLeader( d.getLeader() );
        }
        if ( d.getWarehouse() != null ) {
            productionLine.setWarehouse( warehouseDTOToWarehouse( d.getWarehouse() ) );
        }
        if ( d.getLeaderPhone() != null ) {
            productionLine.setLeaderPhone( d.getLeaderPhone() );
        }
        if ( d.getChejian() != null ) {
            productionLine.setChejian( workShopDTOToWorkShop( d.getChejian() ) );
        }

        return productionLine;
    }

    @Override
    public ProductLineVO toDTO(ProductionLine e) {
        if ( e == null ) {
            return null;
        }

        ProductLineVO productLineVO = new ProductLineVO();

        if ( e.getId() != null ) {
            productLineVO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            productLineVO.setName( e.getName() );
        }
        if ( e.getAddress() != null ) {
            productLineVO.setAddress( e.getAddress() );
        }
        if ( e.getLeader() != null ) {
            productLineVO.setLeader( e.getLeader() );
        }
        if ( e.getWarehouse() != null ) {
            productLineVO.setWarehouse( warehouseToWarehouseDTO( e.getWarehouse() ) );
        }
        if ( e.getLeaderPhone() != null ) {
            productLineVO.setLeaderPhone( e.getLeaderPhone() );
        }
        if ( e.getChejian() != null ) {
            productLineVO.setChejian( workShopToWorkShopDTO( e.getChejian() ) );
        }

        return productLineVO;
    }

    @Override
    public ProductLineVO toVO(ProductionLine entity) {
        if ( entity == null ) {
            return null;
        }

        ProductLineVO productLineVO = new ProductLineVO();

        if ( entity.getWarehouse() != null ) {
            productLineVO.setWarehouse( warehouseToWarehouseDTO( entity.getWarehouse() ) );
        }
        if ( entity.getChejian() != null ) {
            productLineVO.setChejian( workShopToWorkShopDTO( entity.getChejian() ) );
        }
        if ( entity.getId() != null ) {
            productLineVO.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            productLineVO.setName( entity.getName() );
        }
        if ( entity.getAddress() != null ) {
            productLineVO.setAddress( entity.getAddress() );
        }
        if ( entity.getLeader() != null ) {
            productLineVO.setLeader( entity.getLeader() );
        }
        if ( entity.getLeaderPhone() != null ) {
            productLineVO.setLeaderPhone( entity.getLeaderPhone() );
        }

        return productLineVO;
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
}
