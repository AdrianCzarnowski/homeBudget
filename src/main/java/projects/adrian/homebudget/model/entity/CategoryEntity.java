package projects.adrian.homebudget.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JsonIgnore
//    @JsonBackReference
    @JoinColumn(name = "user_id")
//    @JoinTable(name = "homebudget.user_category", joinColumns = {@JoinColumn(name = "userentity_userId")}, inverseJoinColumns = {@JoinColumn(name = "categoryentity_categoryid")})
//    @JoinTable(
//            name = "homebudget.category_apps",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "category_id")}
//    )
    private UserEntity user;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;
}
