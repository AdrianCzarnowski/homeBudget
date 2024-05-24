package projects.adrian.homebudget.model.dto;

import java.util.UUID;

public record CategoryDto(UUID categoryId, UUID userId, String name, String type, String description) {
}
