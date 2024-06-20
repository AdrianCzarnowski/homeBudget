package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(value = "/get-by-id/{uuid}")
    public ResponseEntity<UserDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID uuid, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID uuid) {
        userService.deleteUser(uuid);
        return ResponseEntity.noContent().build();
    }
}


