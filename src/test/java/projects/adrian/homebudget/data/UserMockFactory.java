package projects.adrian.homebudget.data;

import projects.adrian.homebudget.model.dto.UserDto;
import projects.adrian.homebudget.model.entity.UserEntity;

import java.util.UUID;

import static projects.adrian.homebudget.data.TestData.*;

public class UserMockFactory {

    public static UserEntity mockUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setUserId(USER_ID);
        return entity;
    }

    public static UserDto mockUserDto(){
        return mockUserDto(USER_ID);
    }

    public static UserDto mockUserDto(UUID id){
        return new UserDto(id,null,null,null,null,NAME, PASSWORD, EMAIL,null, null);
    }
}
