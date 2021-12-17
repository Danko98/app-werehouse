package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.CategoryDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.service.CategoryService;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


//CREATE
    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategoryRepository(categoryDto);
    }


//READ ALL
    @GetMapping
    public Page<Category> getCategoryPage(@RequestParam int page){
        return categoryService.findAllBy(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }


//READ BY PARENT CATEGORY ID
    @GetMapping(value = "/byParentCategoryId/{categoryId}")
    public Page<Category> getByParentCategoryId(@RequestParam int page, @PathVariable Integer categoryId){
        return categoryService.getByParentCategoryId(categoryId,page);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id,categoryDto);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }


}
