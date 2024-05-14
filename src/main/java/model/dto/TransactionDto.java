package model.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record TransactionDto(UUID transactionId, UserDto user, CategoryDto category, Float amount, String type,
                             Timestamp transactionDate, String description) {
}