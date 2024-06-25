package projects.adrian.homebudget.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import projects.adrian.homebudget.mapper.CategoryMapper;
import projects.adrian.homebudget.model.dto.CategoryDto;
import projects.adrian.homebudget.model.entity.CategoryEntity;
import projects.adrian.homebudget.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class CategoryServiceTest {
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryMapper = Mockito.mock(CategoryMapper.class);
        categoryService = new CategoryService(categoryRepository, categoryMapper);
    }

    @Test
    void getAllCategories_AllCategoriesFound_passed() {
        //given
        CategoryEntity categoryEntity = Mockito.mock(CategoryEntity.class);
        CategoryDto categoryDto = Mockito.mock(CategoryDto.class);

        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(categoryEntity));
        Mockito.when(categoryMapper.toDto(any())).thenReturn(categoryDto);

        //when
        List<CategoryDto> result = categoryService.getAllCategories();

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(categoryDto, result.get(0));
    }

    @Test
    void getCategoryById_CategoryFoundById_passed() {
        //given
        UUID categoryId = UUID.randomUUID();
        CategoryEntity categoryEntity = Mockito.mock(CategoryEntity.class);
        CategoryDto categoryDto = Mockito.mock(CategoryDto.class);

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(categoryEntity));
        Mockito.when(categoryMapper.toDto(any())).thenReturn(categoryDto);

        //when
        CategoryDto result = categoryService.getCategoryById(categoryId);

        //then
        assertNotNull(result);
        assertEquals(categoryDto, result);
    }

    @Test
    void getCategoryById_CategoryNotFoundById_throwException() {
        //given
        UUID categoryId = UUID.randomUUID();

        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //when + then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(categoryId));
        assertEquals("Can not find category by given id " + categoryId, exception.getMessage());
    }

    @Test
    void saveCategory_CategorySaved_passed() {
        //given
        CategoryEntity categoryEntity = Mockito.mock(CategoryEntity.class);
        CategoryDto categoryDto = Mockito.mock(CategoryDto.class);

        Mockito.when(categoryMapper.toEntity(any())).thenReturn(categoryEntity);
        Mockito.when(categoryRepository.save(any())).thenReturn(categoryEntity);
        Mockito.when(categoryMapper.toDto(any())).thenReturn(categoryDto);

        //when
        CategoryDto result = categoryService.saveCategory(categoryDto);

        //then
        assertNotNull(result);
        assertEquals(categoryDto, result);
    }

    @Test
    void deleteCategory_CategoryDeleted_passed() {
        //given
        UUID categoryId = UUID.randomUUID();

        //when
        categoryService.deleteCategory(categoryId);

        //then
        Mockito.verify(categoryRepository).deleteById(categoryId);
    }

}
