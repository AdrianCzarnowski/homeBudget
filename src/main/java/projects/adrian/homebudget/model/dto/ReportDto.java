package projects.adrian.homebudget.model.dto;

import java.util.UUID;

public record ReportDto(UUID reportId, UUID userId, String reportType) {
}
