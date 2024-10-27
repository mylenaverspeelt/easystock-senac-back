package projeto.easystock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.easystock.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByQuantityLessThan(Integer minQuantity);
}
