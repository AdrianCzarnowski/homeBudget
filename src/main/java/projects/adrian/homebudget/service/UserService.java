package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import projects.adrian.homebudget.mapper.UserMapper;
import projects.adrian.homebudget.model.dto.UserDto;
import projects.adrian.homebudget.model.entity.UserEntity;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service

public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto findById(UUID uuid) {
        Optional<UserEntity> optionalUserEntity = userRepository.findById(uuid);
        return optionalUserEntity.map(userMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find user by given id " + uuid));
    }
}
