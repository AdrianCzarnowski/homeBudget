package projects.adrian.homebudget.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.listener.BudgetEntityListener;

import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "budgets")
@Data
@EntityListeners(BudgetEntityListener.class)
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
