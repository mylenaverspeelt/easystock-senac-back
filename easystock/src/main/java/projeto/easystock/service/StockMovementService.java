package projeto.easystock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.easystock.model.Product;
import projeto.easystock.model.StockMovement;
import projeto.easystock.repository.ProductRepository;
import projeto.easystock.repository.StockMovementRepository;

import java.time.LocalDateTime;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository stockMovementRepository;

    @Autowired
    private ProductRepository productRepository;

    // Registrar uma entrada de estoque
    public void addStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setQuantity(product.getQuantity() + quantity);
        productRepository.save(product);

        StockMovement movement = new StockMovement();
        movement.setProduct(product);
        movement.setQuantity(quantity);
        movement.setType("IN");
        movement.setTimestamp(LocalDateTime.now());
        stockMovementRepository.save(movement);
    }

    // Registrar uma saída de estoque
    public void removeStock(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (product.getQuantity() >= quantity) {
            product.setQuantity(product.getQuantity() - quantity);
            productRepository.save(product);

            StockMovement movement = new StockMovement();
            movement.setProduct(product);
            movement.setQuantity(quantity);
            movement.setType("OUT");
            movement.setTimestamp(LocalDateTime.now());
            stockMovementRepository.save(movement);
        } else {
            throw new RuntimeException("Quantidade insuficiente no estoque");
        }
    }
}
