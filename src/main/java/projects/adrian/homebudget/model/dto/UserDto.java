package projects.adrian.homebudget.model.dto;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


public record UserDto(UUID userId, List<ReportDto> reports, List<TransactionDto> transactions, List<BudgetDto> budgets,
                      List<CategoryDto> categories, String name, String password, String email, Timestamp createdAt,
                      Timestamp lastLogin) {

}
