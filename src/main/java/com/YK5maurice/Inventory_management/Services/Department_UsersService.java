package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.Department_UsersDTO.Department_UsersServiceDTO;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Department_Users;
import com.YK5maurice.Inventory_management.Models.Departments;
import com.YK5maurice.Inventory_management.Models.Users;
import com.YK5maurice.Inventory_management.Repository.DepartmentRepo;
import com.YK5maurice.Inventory_management.Repository.Department_UsersRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class Department_UsersService {

    //delcaration des dependences
    private final Department_UsersRepo departmentUsersRepo;
    private final DepartmentService departmentService;
    private final UsersService usersService;

    //constructeur pour injecter les dependences et initialiser nos dependences declarée
    public Department_UsersService (Department_UsersRepo departmentUsersRepo, DepartmentService departmentService, UsersService usersService){
        this.departmentUsersRepo = departmentUsersRepo;
        this.departmentService = departmentService;
        this.usersService = usersService;
    }

    //.....methode pour creer un department_users
    public String insertDepartment_users(long idDepartment, long idUser){
        // pas necessaire car deja gere dans le service ou on les appel
//        if (idDepartment <= 0 || idUser <= 0){
//            throw new ValidationException("l'id du departement et l'id de l'utilisateur ne doivent pas être null et inferieur a 0");
//        }

        try {
            // recuperation du deparetment
            Departments departments = this.departmentService.getDepartementById(idDepartment);

            // recuperation du user
            Users users = this.usersService.getUserById(idUser);

            //creation d'un new departement_users pour l'insertion
            Department_Users departmentUsers = new Department_Users(null,new Date(),departments,users);

            //insertion du deparetement_user
            this.departmentUsersRepo.save(departmentUsers);

            return "Insertion réussie";
        } catch (Exception ex) {
            throw new RuntimeException("Erreur inattendue lors de l'insertion : "+ex);
        }

    }


//    private UsersService usersService;
//    private Department_UsersRepo departmentUsersRepo;
//    private DepartmentService departmentService;
//
//    public Department_UsersService(Department_UsersRepo departmentUsersRepo,UsersService usersService, DepartmentService departmentService){
//        this.departmentUsersRepo=departmentUsersRepo;
//        this.usersService=usersService;
//        this.departmentService=departmentService;
//    }
//
//
//    //inserer un Department_UsersService en bd
//    public String insertDepartment_UsersService(Department_UsersServiceDTO departmentUsersServiceDTO){
//
//        departmentUsersRepo.save(todepartmentUsers(departmentUsersServiceDTO));
//        return "insertion reussi";
//    }
//
//    //service to transforme Department_UsersServiceDTO en Department_UsersService
//    private Department_Users todepartmentUsers(Department_UsersServiceDTO departmentUsersServiceDTO){
//        //recuperer le user
//        Users users = usersService.findByUsername(departmentUsersServiceDTO.getUserName());
//        //recuperer le departement
//        Departments departments = departmentService.findByName(departmentUsersServiceDTO.getDepartmentName());
//        //creer et return un objet Departm
//        return new Department_Users(
//                null,
//                new Date(),
//                departments,
//                users
//        );
//    }
}
