package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.Exceptions.ResourceNoContent;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Departments;
import com.YK5maurice.Inventory_management.Repository.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public DepartmentService(DepartmentRepo departmentRepo){
        this.departmentRepo = departmentRepo;
    }

    //...methode pour getAll departement
    public List<Departments> getAllDepartement(){

        try {
            List<Departments> departmentsList = this.departmentRepo.findAll();

            if (departmentsList.isEmpty()) {
                throw new ResourceNoContent("la liste des departements est vide");
            }
            return departmentsList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //...methode pour getByID departement
    public Departments getDepartementById(long id){
        if (id <= 0){
            throw new ValidationException("le departement avec l'id : "+id+" n'existe pas");
        }
        return this.departmentRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("le departement avec l'id :"+id+" n'existe pas"));
    }

    //...methode pour getByName departement
    public Departments getDepartementByName(String name){
        if (name == null){
            throw new ValidationException("le departement avec le nom : "+name+" n'existe pas");
        }
        Departments departments = departmentRepo.findByName(name);
        if (departments == null){
            throw new ResourceNotFoundException("le departement : "+name+" n'existe pas");
        }
        return this.departmentRepo.findByName(name);
    }


//
//    private DepartmentRepo departmentRepo;
//
//    public DepartmentService(DepartmentRepo departmentRepo){
//        this.departmentRepo=departmentRepo;
//    }
//
//    //recuperer un departement par son nom
//    public Departments findByName(String name){
//            return departmentRepo.findByName(name)
//                    .orElse(null); // Retourne `null` si aucun departement n'est trouvé.
//    }
}
