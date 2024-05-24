package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.model.entity.ReportEntity;
import projects.adrian.homebudget.model.entity.UserEntity;
import projects.adrian.homebudget.service.UserService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.USER_API_URL)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return  ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID uuid){
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity){
        return userService.createUser(userEntity);
    }
}


