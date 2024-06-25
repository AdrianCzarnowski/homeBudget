package projects.adrian.homebudget.repository;

import projects.adrian.homebudget.model.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.adrian.homebudget.model.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, UUID> {

    Optional<BudgetEntity> findByCategoryCategoryId(UUID categoryId);
}
