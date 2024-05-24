package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.CategoryDto;
import projects.adrian.homebudget.model.entity.CategoryEntity;
import projects.adrian.homebudget.service.CategoryService;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping(ApplicationConstants.CATEGORY_API_URL)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(categoryService.getCategoryById(uuid));
    }
}
