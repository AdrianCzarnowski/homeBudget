package projects.adrian.homebudget.mapper;

import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.entity.ReportEntity;
import projects.adrian.homebudget.model.dto.ReportDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING)
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
