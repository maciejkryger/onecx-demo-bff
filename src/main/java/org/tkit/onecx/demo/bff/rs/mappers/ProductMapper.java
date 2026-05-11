package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import gen.org.tkit.onecx.demo.bff.backend.client.model.Product;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.ProductDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {
        CategoryMapper.class })
public interface ProductMapper {
    Product toBackend(ProductDTO source);

    ProductDTO toFrontend(Product source);
}
