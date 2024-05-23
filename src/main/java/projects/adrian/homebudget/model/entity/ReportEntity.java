package projects.adrian.homebudget.model.entity;


import projects.adrian.homebudget.constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "reports")
@Data
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="report_id", nullable = false)
    private UUID reportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "report_type", nullable = false)
    private String reportType;
}
