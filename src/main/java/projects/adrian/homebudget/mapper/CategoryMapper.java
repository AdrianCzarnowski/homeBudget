package projects.adrian.homebudget.mapper;

import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.CategoryDto;
import projects.adrian.homebudget.model.entity.CategoryEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING, uses = UserMapper.class)
public interface CategoryMapper {
    @IterableMapping(qualifiedByName = "toDto")
    List<CategoryDto> toListDto(List<CategoryEntity> categoryEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<CategoryEntity> toListEntity(List<CategoryDto> categoryDtos);

    @Named("toDto")
    CategoryDto toDto(CategoryEntity categoryEntity);

    @Named("toEntity")
    CategoryEntity toEntity(CategoryDto categoryDto);
}
