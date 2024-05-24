package projects.adrian.homebudget.model.entity;

import projects.adrian.homebudget.constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CategoryEntity> categories = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<BudgetEntity> budgets = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TransactionEntity> transactions = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ReportEntity> reports = new ArrayList<>();

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "last_login", nullable = false)
    private Timestamp lastLogin;

}
