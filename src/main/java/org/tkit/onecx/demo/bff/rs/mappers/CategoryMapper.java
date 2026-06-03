package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;

import gen.org.tkit.onecx.demo.bff.backend.client.model.Category;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CategoryDTO;

@Mapper
public interface CategoryMapper {
    @BeanMapping(ignoreByDefault = true)
    Category toBackend(CategoryDTO source);

    @BeanMapping(ignoreByDefault = true)
    CategoryDTO toFrontend(Category source);
}
