package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.mapper.CategoryMapper;
import projects.adrian.homebudget.model.dto.CategoryDto;
import projects.adrian.homebudget.model.entity.CategoryEntity;
import projects.adrian.homebudget.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }

    public CategoryDto getCategoryById(UUID uuid) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(uuid);
        return optionalCategoryEntity.map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find user by given id " + uuid));
    }




}