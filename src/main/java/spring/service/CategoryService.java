package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.model.Category;
import spring.repository.ICategoryRepository;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    ICategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
