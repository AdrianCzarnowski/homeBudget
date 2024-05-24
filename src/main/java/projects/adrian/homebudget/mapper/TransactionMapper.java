package projects.adrian.homebudget.mapper;

import org.mapstruct.Mapping;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.TransactionDto;
import projects.adrian.homebudget.model.entity.TransactionEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING)
public interface TransactionMapper {

    @IterableMapping(qualifiedByName = "toDto")
    List<TransactionDto> toListDto(List<TransactionEntity> transactionEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<TransactionEntity> toListEntity(List<TransactionDto> transactionDtos);

    @Mapping(source = "user.userId", target = "userId")
    @Named("toDto")
    TransactionDto toDto(TransactionEntity transactionEntity);

    @Named("toEntity")
    TransactionEntity toEntity(TransactionDto transactionDto);
}
