package repository;

import entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BudgetRepository extends JpaRepository<BudgetEntity, UUID> {
    List<BudgetEntity> findByUserName(String name);
}
