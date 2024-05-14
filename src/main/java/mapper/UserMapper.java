package mapper;

import constants.ApplicationConstants;
import model.dto.UserDto;
import model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING, uses = ReportMapper.class)
public interface UserMapper {

    @Mapping(target = "name", source = "userName")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", qualifiedByName = "addSuffixToEmail")
    UserDto toDto(UserEntity userEntity);

    @Mapping(target = "userName", source = "name")
    UserEntity toEntity(UserDto userDto);

    @Named("addSuffixToEmail")
    default String addSuffixToEmail(String email){
        return email+"_test";
    }
}
