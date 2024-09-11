package projects.adrian.homebudget.rest;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="userService", url = "http://localhost:8888", path = "/users")
public interface UserServiceClient {

    @GetMapping("/get-user")
    String getUser();

}
