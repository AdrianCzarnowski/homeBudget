package projects.adrian.homebudget.model.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record BudgetDto(UUID budgetId, UUID userId, UUID categoryId, Float amount,
                        Integer monthDt, Integer yearDt, Timestamp startTime, Timestamp endTime, Timestamp generatedDate) {
}
