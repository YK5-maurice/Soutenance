package com.YK5maurice.Inventory_management.Controller;

import com.YK5maurice.Inventory_management.DTO.CategorieDTO.CreateCategorieDTO;
import com.YK5maurice.Inventory_management.Models.Categories;
import com.YK5maurice.Inventory_management.Services.CategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategorieController {

    private final CategoriesService categoriesService;

    public CategorieController(CategoriesService categoriesService){
        this.categoriesService = categoriesService;
    }

    //...methode getAllCategorie
    @GetMapping("")
    public ResponseEntity<List<Categories>> getAllCategorie(){
        List<Categories> categoriesList = categoriesService.getAllCategories();

        return ResponseEntity.ok(categoriesList);
    }

    //...methode getCategorieByName
    @GetMapping("/Name")
    public ResponseEntity<Categories> getCategoriesByName(@RequestParam String name){
        Categories categorie = categoriesService.getCategorieByName(name);

        return ResponseEntity.ok(categorie);
    }

    //...methode getCategorieById
    @GetMapping("/Id")
    public ResponseEntity<Categories> getCategoriesById(@RequestParam long id){
        Categories categorie = categoriesService.getCategoriesById(id);

        return ResponseEntity.ok(categorie);
    }

    //...methode CreateCategories
    @PostMapping("/createCategories")
    public ResponseEntity<Categories> CreateCategories(@RequestBody CreateCategorieDTO createCategorieDTO){



        Categories categories = categoriesService.cerateCategorie(createCategorieDTO);
        return ResponseEntity.ok(categories);
    }
}
