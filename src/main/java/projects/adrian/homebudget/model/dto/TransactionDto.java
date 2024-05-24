package projects.adrian.homebudget.model.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record TransactionDto(UUID transactionId, UUID userId, CategoryDto category, Float amount, String type,
                             Timestamp transactionDate, String description) {
}
