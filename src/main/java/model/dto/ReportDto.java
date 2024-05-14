package model.dto;

import java.util.UUID;

public record ReportDto(UUID reportId, UserDto user, String reportType) {
}
