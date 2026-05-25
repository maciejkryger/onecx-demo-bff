package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import gen.org.tkit.onecx.demo.bff.backend.client.model.Category;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CategoryDTO;

@Mapper(componentModel = "jakarta-cdi", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category map(CategoryDTO source);

    CategoryDTO map(Category source);

}
