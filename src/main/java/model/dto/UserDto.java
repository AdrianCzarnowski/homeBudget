package model.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public record UserDto(UUID userId, List<ReportDto> reports, List<TransactionDto> transactions, List<BudgetDto> budgets,
                      List<CategoryDto> categories, String name, String password, String email, Timestamp createdAt,
                      Timestamp lastLogin) {
}
