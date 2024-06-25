package projects.adrian.homebudget.model.entity;

import projects.adrian.homebudget.constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "budgets")
@Data
public class BudgetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "budget_id", nullable = false)
    private UUID budgetId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(name = "amount", columnDefinition = "FLOAT")
    private Float amount;

    @Column(name = "month_dt", nullable = false)
    private Integer monthDt;

    @Column(name = "year_dt", nullable = false)
    private Integer yearDt;


}
