package com.gabchak.controller;

import com.gabchak.controller.external.model.CategoryDto;
import com.gabchak.model.Category;
import com.gabchak.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ModelAndView showAllCategories(ModelAndView vm) {
        vm.setViewName("categories");
        vm.addObject("categories", categoryService.findAll());
        return vm;
    }

    @GetMapping("/category_{id}")
    public ModelAndView findCategoryById(@PathVariable Long id, ModelAndView vm) {
        vm.setViewName("category");
        vm.addObject("category", categoryService.findByIdWithProductList(id));
        return vm;
    }

    @GetMapping("/{categoryName}")
    public ModelAndView findCategoryByName(@PathVariable String categoryName, ModelAndView vm) {
        Category category = categoryService.findByName(categoryName);
        vm.setViewName("category");
        vm.addObject("category", categoryService.findByIdWithProductList(category.getId()));

        return vm;
    }

    @GetMapping("/admin/categories")
    public ModelAndView categoryAdminList(ModelAndView vm) {
        vm.setViewName("categoryAdminList");
        vm.addObject("categories", categoryService.findAll());
        return vm;
    }

    @GetMapping("/admin/categories/create_category")
    public ModelAndView createCategoryPage(ModelAndView vm) {
        vm.setViewName("categoryCreate");
        vm.addObject("category", CategoryDto.empty());
        return vm;
    }

    @PostMapping("/admin/categories/create_category")
    public ModelAndView createCategory(CategoryDto categoryDto, ModelAndView vm) {
        categoryService.addCategory(Category.of(categoryDto));
        vm.setViewName("categoryAdminList");
        vm.addObject("categories", categoryService.findAll());
        return vm;
    }

    @PostMapping("/admin/categories/{id}_delete")
    public ModelAndView deleteCategory(@PathVariable Long id, ModelAndView vm) {
        categoryService.deleteById(id);
        vm.setViewName("categoryAdminList");
        vm.addObject("categories", categoryService.findAll());
        return vm;
    }

    @GetMapping("/admin/categories/edit_{id}")
    public ModelAndView editCategoryPage(@PathVariable Long id, ModelAndView vm) {
        vm.setViewName("categoryEdit");
        vm.addObject("category", categoryService.findById(id));
        return vm;
    }

    @PostMapping("/admin/categories/save_{id}")
    public ModelAndView saveCategory(@PathVariable Long id, Category category, ModelAndView vm) {
        category.setId(id);
        categoryService.update(category);
        vm.setViewName("categoryAdminList");
        vm.addObject("categories", categoryService.findAll());
        return vm;
    }
}
