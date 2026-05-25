package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import gen.org.tkit.onecx.demo.bff.backend.client.model.Product;
import gen.org.tkit.onecx.demo.bff.backend.client.model.ProductPageResult;
import gen.org.tkit.onecx.demo.bff.backend.client.model.ProductSearchCriteria;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CreateProductRequestDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CreateProductResponseDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.ProductDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.SearchProductRequestDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.SearchProductResponseDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.UpdateProductRequestDTO;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.UpdateProductResponseDTO;

@Mapper(componentModel = "jakarta-cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { CategoryMapper.class })
public interface ProductMapper {
    Product map(ProductDTO source);

    ProductDTO map(Product source);

    ProductSearchCriteria map(SearchProductRequestDTO source);

    @org.mapstruct.Named("toSearchProductResponse")
    SearchProductResponseDTO toSearchProductResponse(ProductPageResult source);

    Product map(CreateProductRequestDTO source);

    @org.mapstruct.Named("toCreateProductResponse")
    CreateProductResponseDTO toCreateProductResponse(Product source);

    Product map(UpdateProductRequestDTO source);

    @org.mapstruct.Named("toUpdateProductResponse")
    UpdateProductResponseDTO toUpdateProductResponse(Product source);

}
