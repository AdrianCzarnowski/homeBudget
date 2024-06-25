package projects.adrian.homebudget.data;

import projects.adrian.homebudget.model.entity.UserEntity;

import static projects.adrian.homebudget.data.UserTestData.USER_ID;

public class UserMockFactory {

    public static UserEntity mockUserEntity() {
        UserEntity entity = new UserEntity();
        entity.setUserId(USER_ID);
        return entity;
    }
}
