package projects.adrian.homebudget.mapper;

import org.mapstruct.Mapping;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.entity.BudgetEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING)
public interface BudgetMapper {
    @IterableMapping(qualifiedByName = "toDto")
    List<BudgetDto> toListDto(List<BudgetEntity> budgetEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<BudgetEntity> toListEntity(List<BudgetDto> budgetDtos);

    @Mapping(source = "user.userId", target = "userId")
    @Named("toDto")
    BudgetDto toDto(BudgetEntity budgetEntity);

    @Named("toEntity")
    BudgetEntity toEntity(BudgetDto budgetDto);
}
