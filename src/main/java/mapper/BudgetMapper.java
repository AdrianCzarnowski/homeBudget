package mapper;

import constants.ApplicationConstants;
import model.dto.BudgetDto;
import model.entity.BudgetEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING, uses = {UserMapper.class, CategoryMapper.class})
public interface BudgetMapper {
    @IterableMapping(qualifiedByName = "toDto")
    List<BudgetDto> toListDto(List<BudgetEntity> budgetEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<BudgetEntity> toListEntity(List<BudgetDto> budgetDtos);

    @Named("toDto")
    BudgetDto toDto(BudgetEntity budgetEntity);

    @Named("toEntity")
    BudgetEntity toEntity(BudgetDto budgetDto);
}
