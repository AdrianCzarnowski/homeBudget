package model.dto;

import java.util.UUID;

public record CategoryDto(UUID categoryId, UserDto user, String name, String type, String description) {
}
