package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

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

@Mapper
public interface ProductMapper {
    @BeanMapping(ignoreByDefault = true)
    Product toBackend(ProductDTO source);

    @BeanMapping(ignoreByDefault = true)
    ProductDTO toFrontend(Product source);

    @BeanMapping(ignoreByDefault = true)
    ProductSearchCriteria map(SearchProductRequestDTO source);

    @BeanMapping(ignoreByDefault = true)
    SearchProductResponseDTO toSearchProductResponse(ProductPageResult source);

    @BeanMapping(ignoreByDefault = true)
    Product map(CreateProductRequestDTO source);

    @BeanMapping(ignoreByDefault = true)
    CreateProductResponseDTO toCreateProductResponse(Product source);

    @BeanMapping(ignoreByDefault = true)
    Product map(UpdateProductRequestDTO source);

    @BeanMapping(ignoreByDefault = true)
    UpdateProductResponseDTO toUpdateProductResponse(Product source);
}
