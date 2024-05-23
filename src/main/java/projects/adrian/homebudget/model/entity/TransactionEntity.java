package projects.adrian.homebudget.model.entity;

import projects.adrian.homebudget.constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "transactions")
@Data
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="transaction_id", nullable = false)
    private UUID transactionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "amount", columnDefinition = "FLOAT")
    private Float amount;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "transaction_date", nullable = false)
    private Timestamp transactionDate;

    @Column(name = "description", nullable = false)
    private String description;
}
