package projects.adrian.homebudget.model.entity;

import projects.adrian.homebudget.constants.ApplicationConstants;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(schema = ApplicationConstants.SCHEMA_DB, name = "categories")
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name ="category_id", nullable = false)
    private UUID categoryId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;
}
