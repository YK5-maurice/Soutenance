package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.CategorieDTO.CreateCategorieDTO;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNoContent;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Categories;
import com.YK5maurice.Inventory_management.Repository.CategoriesRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
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
            throw new ValidationException("la liste des Categories est vide");
        }
        try {
            return categoriesList;
        } catch (Exception e) {
            throw new RuntimeException("ERREUR LORS DE LA RECUPERATION DES DONNEES : "+e);
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
    public Categories getCategoriesById(long id){
        if (id <= 0 ){
            throw new ValidationException("l'id doit etre strictement superieur a 0");
        }
        return this.categoriesRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("la categorie avec l'id : "+id+" n'existe pas"));
    }

    //.... methode create categorie
    public Categories cerateCategorie(CreateCategorieDTO createCategorieDTO){
        if (createCategorieDTO == null){
            throw new ValidationException("l'objet categories envoye est incorect");
        }
        //....verifie si la categorie existe deja
        if(this.categoriesRepo.findByName(createCategorieDTO.getName()) != null){
            throw new ResourceNoContent("la categorie avec le nom "+createCategorieDTO.getName()+" existe dejea");
        }
        //creation d'un objet Categorie qui sera initialiser et save par la suite
        Categories categories = new Categories();

        categories.setName(createCategorieDTO.getName());
        categories.setDescription(createCategorieDTO.getDescription());
        categories.setCreated_at(new Date());
        try{
            return categoriesRepo.save(categories);
        } catch (Exception e) {
            throw new RuntimeException("ERREUR LORS DE L'INSERTION DE L'OBJET CATEGORIE EN BD : "+e);
        }
    }
}
