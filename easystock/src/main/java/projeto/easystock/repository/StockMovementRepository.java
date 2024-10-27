package projeto.easystock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.easystock.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {
}

