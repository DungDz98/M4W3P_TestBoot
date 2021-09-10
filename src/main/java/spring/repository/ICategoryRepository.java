package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
}
