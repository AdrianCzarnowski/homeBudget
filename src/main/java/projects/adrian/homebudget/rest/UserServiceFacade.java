package projects.adrian.homebudget.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserServiceFacade {
    private UserServiceClient userServiceClient;
    public String getUser(){
        return userServiceClient.getUser();
    }
}
