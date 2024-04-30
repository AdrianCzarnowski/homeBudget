package entity;

import constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private CategoryEntity category;

    @Column(name = "amount", columnDefinition = "FLOAT")
    private Float amount;

    @Column(name = "month_dt", nullable = false)
    @Enumerated(EnumType.STRING)
    private Month monthDt;

    @Column(name = "year_dt", nullable = false)
    private Year yearDt;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "generated_date", nullable = false)
    private Timestamp generatedDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CategoryEntity> categories = new ArrayList<>();
}
