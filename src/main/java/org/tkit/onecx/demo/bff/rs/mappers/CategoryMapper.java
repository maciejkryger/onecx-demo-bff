package org.tkit.onecx.demo.bff.rs.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.tkit.quarkus.rs.mappers.OffsetDateTimeMapper;

import gen.org.tkit.onecx.demo.bff.client.model.Category;
import gen.org.tkit.onecx.demo.bff.rs.internal.model.CategoryDTO;

@Mapper(uses = { OffsetDateTimeMapper.class })
public interface CategoryMapper {
    @BeanMapping(ignoreByDefault = true)
    Category toBackend(CategoryDTO source);

    @BeanMapping(ignoreByDefault = true)
    CategoryDTO toFrontend(Category source);
}
