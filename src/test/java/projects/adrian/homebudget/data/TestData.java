package projects.adrian.homebudget.data;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class TestData {
    public static final UUID USER_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public static final String NAME = "Adrian";
    public static final String PASSWORD = "asfdsds";
    public static final String EMAIL = "sdsa@wp.pl";

    //Transaction

    public static final UUID TRANSACTION_ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174220");
}
