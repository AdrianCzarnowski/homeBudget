package projects.adrian.homebudget.model.dto;

import java.sql.Timestamp;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

public record BudgetDto(UUID budgetID, UserDto user, CategoryDto category, Float amount,
                        Integer monthDt, Integer yearDt, Timestamp startTime, Timestamp endTime, Timestamp generatedDate) {
}
