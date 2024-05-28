package projects.adrian.homebudget.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.adrian.homebudget.constants.ApplicationConstants;
import projects.adrian.homebudget.model.dto.BudgetDto;
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
    public ResponseEntity<List<CategoryDto>> getAllBudgets(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryDto> getById(@PathVariable UUID uuid) {
        return ResponseEntity.ok(categoryService.getCategoryById(uuid));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{uuid}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable UUID uuid, @RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryDto), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{uuid}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable UUID uuid) {
        categoryService.deleteCategory(
                uuid);
        return ResponseEntity.noContent().build();
    }
}
