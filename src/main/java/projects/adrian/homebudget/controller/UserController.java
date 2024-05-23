package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import projects.adrian.homebudget.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.service.UserService;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(UserController.API_URL)
public class UserController {

    public static final String API_URL = "/api/user";

    private final UserService userService;

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.findById(uuid));
    }


}


