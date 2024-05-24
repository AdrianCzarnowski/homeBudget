package projects.adrian.homebudget.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.UserDto;
import projects.adrian.homebudget.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = ApplicationConstants.COMPONENT_MODEL_SPRING,uses = {CategoryMapper.class,TransactionMapper.class, ReportMapper.class, BudgetMapper.class})

public abstract class UserMapper {

    @Mapping(target = "name", source = "userName")
    @Mapping(target = "password", ignore = true)
//    @Mapping(target = "email", qualifiedByName = "addSuffixToEmail")
    public abstract  UserDto toDto(UserEntity userEntity);

    @Mapping(target = "userName", source = "name")
    abstract UserEntity toEntity(UserDto userDto);

//    @Named("addSuffixToEmail")
//     String addSuffixToEmail(String email) {
//        return email + "_test";
//    }
}
