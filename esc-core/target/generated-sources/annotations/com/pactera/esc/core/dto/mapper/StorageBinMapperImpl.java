package com.pactera.esc.core.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:52+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class StorageBinMapperImpl implements StorageBinMapper {

    @Override
    public StorageBinDTO toDTO(StorageBin entity) {
        if ( entity == null ) {
            return null;
        }

        StorageBinDTO storageBinDTO = new StorageBinDTO();

        String name = entityMaterialName( entity );
        if ( name != null ) {
            storageBinDTO.setMaterialName( name );
        }
        String id = entityShelvesId( entity );
        if ( id != null ) {
            storageBinDTO.setShelvesId( id );
        }
        String id1 = entityProductId( entity );
        if ( id1 != null ) {
            storageBinDTO.setProductId( id1 );
        }
        String id2 = entityWarehouseId( entity );
        if ( id2 != null ) {
            storageBinDTO.setWarehouseId( id2 );
        }
        String code = entityMaterialCode( entity );
        if ( code != null ) {
            storageBinDTO.setMaterialCode( code );
        }
        String code1 = entityShelvesCode( entity );
        if ( code1 != null ) {
            storageBinDTO.setShelvesCode( code1 );
        }
        String id3 = entityMaterialId( entity );
        if ( id3 != null ) {
            storageBinDTO.setMaterialId( id3 );
        }
        String name1 = entityWarehouseName( entity );
        if ( name1 != null ) {
            storageBinDTO.setWarehouseName( name1 );
        }
        String name2 = entityProductName( entity );
        if ( name2 != null ) {
            storageBinDTO.setProductName( name2 );
        }
        if ( entity.getId() != null ) {
            storageBinDTO.setId( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            storageBinDTO.setCode( entity.getCode() );
        }
        List<TagDTO> list = tagListToTagDTOList( entity.getFaStorehouseTag() );
        if ( list != null ) {
            storageBinDTO.setFaStorehouseTag( list );
        }
        if ( entity.getColumnNum() != null ) {
            storageBinDTO.setColumnNum( entity.getColumnNum() );
        }
        if ( entity.getRowNum() != null ) {
            storageBinDTO.setRowNum( entity.getRowNum() );
        }
        if ( entity.getCreator() != null ) {
            storageBinDTO.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            storageBinDTO.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            storageBinDTO.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            storageBinDTO.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getPageable() != null ) {
            storageBinDTO.setPageable( entity.getPageable() );
        }
        List list1 = entity.getInboundFaStoreHouse();
        if ( list1 != null ) {
            storageBinDTO.setInboundFaStoreHouse( new ArrayList( list1 ) );
        }
        if ( entity.getInboundNum() != null ) {
            storageBinDTO.setInboundNum( entity.getInboundNum() );
        }
        if ( entity.getNum() != null ) {
            storageBinDTO.setNum( entity.getNum() );
        }
        if ( entity.getState() != null ) {
            storageBinDTO.setState( entity.getState() );
        }

        return storageBinDTO;
    }

    @Override
    public StorageBin toEntity(StorageBinDTO entity) {
        if ( entity == null ) {
            return null;
        }

        StorageBin storageBin = new StorageBin();

        storageBin.setWarehouse( storageBinDTOToWarehouse( entity ) );
        storageBin.setShelves( storageBinDTOToShelves( entity ) );
        storageBin.setMaterial( storageBinDTOToMaterial( entity ) );
        storageBin.setProduct( storageBinDTOToProduct( entity ) );
        if ( entity.getCreator() != null ) {
            storageBin.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            storageBin.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            storageBin.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            storageBin.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getId() != null ) {
            storageBin.setId( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            storageBin.setCode( entity.getCode() );
        }
        if ( entity.getState() != null ) {
            storageBin.setState( entity.getState() );
        }
        if ( entity.getColumnNum() != null ) {
            storageBin.setColumnNum( entity.getColumnNum() );
        }
        if ( entity.getRowNum() != null ) {
            storageBin.setRowNum( entity.getRowNum() );
        }
        if ( entity.getNum() != null ) {
            storageBin.setNum( entity.getNum() );
        }
        List<Tag> list = tagDTOListToTagList( entity.getFaStorehouseTag() );
        if ( list != null ) {
            storageBin.setFaStorehouseTag( list );
        }
        if ( entity.getPageable() != null ) {
            storageBin.setPageable( entity.getPageable() );
        }
        List list1 = entity.getInboundFaStoreHouse();
        if ( list1 != null ) {
            storageBin.setInboundFaStoreHouse( new ArrayList( list1 ) );
        }
        if ( entity.getInboundNum() != null ) {
            storageBin.setInboundNum( entity.getInboundNum() );
        }

        return storageBin;
    }

    private String entityMaterialName(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Material material = storageBin.getMaterial();
        if ( material == null ) {
            return null;
        }
        String name = material.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityShelvesId(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Shelves shelves = storageBin.getShelves();
        if ( shelves == null ) {
            return null;
        }
        String id = shelves.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityProductId(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Product product = storageBin.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseId(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Warehouse warehouse = storageBin.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityMaterialCode(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Material material = storageBin.getMaterial();
        if ( material == null ) {
            return null;
        }
        String code = material.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityShelvesCode(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Shelves shelves = storageBin.getShelves();
        if ( shelves == null ) {
            return null;
        }
        String code = shelves.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityMaterialId(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Material material = storageBin.getMaterial();
        if ( material == null ) {
            return null;
        }
        String id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Warehouse warehouse = storageBin.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityProductName(StorageBin storageBin) {
        if ( storageBin == null ) {
            return null;
        }
        Product product = storageBin.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected TagDTO tagToTagDTO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        if ( tag.getId() != null ) {
            tagDTO.setId( tag.getId() );
        }
        if ( tag.getName() != null ) {
            tagDTO.setName( tag.getName() );
        }
        if ( tag.getCreator() != null ) {
            tagDTO.setCreator( tag.getCreator() );
        }
        if ( tag.getOperator() != null ) {
            tagDTO.setOperator( tag.getOperator() );
        }
        if ( tag.getCreateTime() != null ) {
            tagDTO.setCreateTime( tag.getCreateTime() );
        }
        if ( tag.getUpdateTime() != null ) {
            tagDTO.setUpdateTime( tag.getUpdateTime() );
        }
        if ( tag.getValue() != null ) {
            tagDTO.setValue( tag.getValue() );
        }
        if ( tag.getPriority() != null ) {
            tagDTO.setPriority( tag.getPriority() );
        }

        return tagDTO;
    }

    protected List<TagDTO> tagListToTagDTOList(List<Tag> list) {
        if ( list == null ) {
            return null;
        }

        List<TagDTO> list1 = new ArrayList<TagDTO>( list.size() );
        for ( Tag tag : list ) {
            list1.add( tagToTagDTO( tag ) );
        }

        return list1;
    }

    protected Warehouse storageBinDTOToWarehouse(StorageBinDTO storageBinDTO) {
        if ( storageBinDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( storageBinDTO.getWarehouseId() != null ) {
            warehouse.setId( storageBinDTO.getWarehouseId() );
        }
        if ( storageBinDTO.getWarehouseName() != null ) {
            warehouse.setName( storageBinDTO.getWarehouseName() );
        }

        return warehouse;
    }

    protected Shelves storageBinDTOToShelves(StorageBinDTO storageBinDTO) {
        if ( storageBinDTO == null ) {
            return null;
        }

        Shelves shelves = new Shelves();

        if ( storageBinDTO.getShelvesCode() != null ) {
            shelves.setCode( storageBinDTO.getShelvesCode() );
        }
        if ( storageBinDTO.getShelvesId() != null ) {
            shelves.setId( storageBinDTO.getShelvesId() );
        }

        return shelves;
    }

    protected Material storageBinDTOToMaterial(StorageBinDTO storageBinDTO) {
        if ( storageBinDTO == null ) {
            return null;
        }

        Material material = new Material();

        if ( storageBinDTO.getMaterialCode() != null ) {
            material.setCode( storageBinDTO.getMaterialCode() );
        }
        if ( storageBinDTO.getMaterialId() != null ) {
            material.setId( storageBinDTO.getMaterialId() );
        }
        if ( storageBinDTO.getMaterialName() != null ) {
            material.setName( storageBinDTO.getMaterialName() );
        }

        return material;
    }

    protected Product storageBinDTOToProduct(StorageBinDTO storageBinDTO) {
        if ( storageBinDTO == null ) {
            return null;
        }

        Product product = new Product();

        if ( storageBinDTO.getProductId() != null ) {
            product.setId( storageBinDTO.getProductId() );
        }
        if ( storageBinDTO.getProductName() != null ) {
            product.setName( storageBinDTO.getProductName() );
        }

        return product;
    }

    protected Tag tagDTOToTag(TagDTO tagDTO) {
        if ( tagDTO == null ) {
            return null;
        }

        Tag tag = new Tag();

        if ( tagDTO.getCreator() != null ) {
            tag.setCreator( tagDTO.getCreator() );
        }
        if ( tagDTO.getOperator() != null ) {
            tag.setOperator( tagDTO.getOperator() );
        }
        if ( tagDTO.getCreateTime() != null ) {
            tag.setCreateTime( tagDTO.getCreateTime() );
        }
        if ( tagDTO.getUpdateTime() != null ) {
            tag.setUpdateTime( tagDTO.getUpdateTime() );
        }
        if ( tagDTO.getId() != null ) {
            tag.setId( tagDTO.getId() );
        }
        if ( tagDTO.getName() != null ) {
            tag.setName( tagDTO.getName() );
        }
        if ( tagDTO.getValue() != null ) {
            tag.setValue( tagDTO.getValue() );
        }
        if ( tagDTO.getPriority() != null ) {
            tag.setPriority( tagDTO.getPriority() );
        }

        return tag;
    }

    protected List<Tag> tagDTOListToTagList(List<TagDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Tag> list1 = new ArrayList<Tag>( list.size() );
        for ( TagDTO tagDTO : list ) {
            list1.add( tagDTOToTag( tagDTO ) );
        }

        return list1;
    }
}
