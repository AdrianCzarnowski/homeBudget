package entity;


import constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "reports")
@Data
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="reports", nullable = false)
    private UUID categoryId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "report_type", nullable = false)
    private String reportType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "budgetId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BudgetEntity> categories = new ArrayList<>();
}
