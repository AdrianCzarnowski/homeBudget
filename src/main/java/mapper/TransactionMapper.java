package mapper;

import constants.ApplicationConstants;
import model.dto.TransactionDto;
import model.entity.TransactionEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING, uses = {CategoryMapper.class,UserMapper.class})
public interface TransactionMapper {

    @IterableMapping(qualifiedByName = "toDto")
    List<TransactionDto> toListDto(List<TransactionEntity> transactionEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<TransactionEntity> toListEntity(List<TransactionDto> transactionDtos);

    @Named("toDto")
    TransactionDto toDto(TransactionEntity transactionEntity);

    @Named("toEntity")
    TransactionEntity toEntity(TransactionDto transactionDto);


}
