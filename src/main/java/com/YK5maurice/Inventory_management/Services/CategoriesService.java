package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.Exceptions.ResourceNoContent;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Categories;
import com.YK5maurice.Inventory_management.Models.EnumTypeRole;
import com.YK5maurice.Inventory_management.Models.Roles;
import com.YK5maurice.Inventory_management.Repository.CategoriesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    //declarion des dependences/variables
    private final CategoriesRepo categoriesRepo;

    //constructeur pour les initialisation et injection de dependence
    public CategoriesService (CategoriesRepo categoriesRepo){
        this.categoriesRepo = categoriesRepo;
    }

    // .... methode pour get all categories
    public List<Categories> getAllCategories(){

        List<Categories> categoriesList = this.categoriesRepo.findAll();

        if (categoriesList.isEmpty()){
            throw new ResourceNoContent("la liste des Categories est vide");
        }
        try {
            return categoriesList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //... methode pour get categorie by name
    public Categories getCategorieByName(String name){

        if(name == null){
            throw new ValidationException("le name ne doit pas être null");
        }

        Categories categories = this.categoriesRepo.findByName(name);
        if (categories == null){
            throw new ResourceNotFoundException("la categorie : "+name+" n'existe pas");
        }
        return categories;
    }

    //...methode pour get categorie by id
    public Categories get getCategoriesById(long id){
        if (id <= 0 ){
            throw new ValidationException("l'id doit etre strictement superieur a 0");
        }
        Categories categories = this.categoriesRepo.findById(id);
    }
}
