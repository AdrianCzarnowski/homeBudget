package projects.adrian.homebudget.helper;

import lombok.experimental.UtilityClass;
import projects.adrian.homebudget.constants.ApplicationConstants;
@UtilityClass
public class TestUtils {

    public static String getUrl(String path, int port) {
        return "http://localhost:" + port + path;
    }

}
