package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import gen.org.tkit.onecx.demo.bff.backend.client.model.Category;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CategoryDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category toBackend(CategoryDTO source);

    CategoryDTO toFrontend(Category source);
}
