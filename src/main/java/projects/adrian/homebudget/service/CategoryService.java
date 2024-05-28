package projects.adrian.homebudget.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projects.adrian.homebudget.mapper.CategoryMapper;
import projects.adrian.homebudget.model.dto.BudgetDto;
import projects.adrian.homebudget.model.dto.CategoryDto;
import projects.adrian.homebudget.model.entity.BudgetEntity;
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

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    public CategoryDto getCategoryById(UUID uuid) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(uuid);
        return optionalCategoryEntity.map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find category by given id " + uuid));
    }

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryDto);
        CategoryEntity savedEntity = categoryRepository.save(categoryEntity);
        return categoryMapper.toDto(savedEntity);
    }

    public void deleteCategory(UUID userId) {
        categoryRepository.deleteById(userId);
    }
}
