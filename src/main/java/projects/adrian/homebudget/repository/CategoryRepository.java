package projects.adrian.homebudget.repository;

import projects.adrian.homebudget.model.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    Optional<CategoryEntity> findByName(String name);
}
