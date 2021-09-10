package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
