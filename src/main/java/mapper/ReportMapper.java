package mapper;

import constants.ApplicationConstants;
import model.entity.ReportEntity;
import model.dto.ReportDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING, uses = UserMapper.class)
public interface ReportMapper {

    @IterableMapping(qualifiedByName = "toDto")
    List<ReportDto> toListDto(List<ReportEntity> reportEntities);
    @IterableMapping(qualifiedByName = "toEntity")
    List<ReportEntity> toListEntity(List<ReportDto> reportDtos);

    @Named("toDto")
    ReportDto toDto(ReportEntity reportEntity);

    @Named("toEntity")
    ReportEntity toEntity(ReportDto reportDto);
}
