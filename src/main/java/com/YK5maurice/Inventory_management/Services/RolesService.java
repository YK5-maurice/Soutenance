package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.RoleDTO.RolesDTO;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNoContent;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.EnumTypeRole;
import com.YK5maurice.Inventory_management.Models.Roles;
import com.YK5maurice.Inventory_management.Repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RolesService {

    private final RoleRepo roleRepo;
    //constructeur
    public RolesService( RoleRepo roleRepo){
        this.roleRepo=roleRepo;
    }

    //...methode get All role
    public List<Roles> getAllRoles(){
        List<Roles> rolesList = this.roleRepo.findAll();

        if (rolesList.isEmpty()){
            throw new ResourceNoContent("la liste des Roles est vide");
        }
        try {
           return rolesList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //....methode get a role byName
    public Roles getRoleByName(String role){
        if(role == null){
            throw new ValidationException("le role ne doit pas être null");
        }
        //convertir le string role en Enum role
        EnumTypeRole roleEnum = Enum.valueOf(EnumTypeRole.class, role);
        Roles roles = roleRepo.findByName(roleEnum);
        if (roles == null){
            throw new ResourceNotFoundException("le role : "+role+" n'existe pas");
        }
        return this.roleRepo.findByName(roleEnum);
//        try {
//            //transformation du roleDTO en Role
//            Roles roles = rolesDTO_TO_Role(rolesDTO);
//            //la persistance du role
//            roleRepo.save(roles);
//
//            return "le role a bien été ajouté";
//        } catch (Exception ex) {
//            throw new RuntimeException("Une erreur est survenue lors de l'ajout du rôle. : "+ex)
//        }
    }


    //....methode getRoleById
    public Roles getRoleById(long id){
        if (id <= 0){
            throw new ValidationException("l'id ne dois pas etre 0");
        }
        return this.roleRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("le role avec l'id :"+id+" n'existe pas"));
    }

//    RoleRepo roleRepo ;
//
//    public RolesService(RoleRepo roleRepo){
//        this.roleRepo=roleRepo;
//    }
//
//    public List<Roles> getRoles(){
//        return roleRepo.findAll();
//    }
//
//    public Roles getRoleByName(String roleName) {
//        EnumTypeRole roleEnum = EnumTypeRole.valueOf(roleName);  // Convertit la chaîne en Enum
//        return roleRepo.findByName(roleEnum);
//    }

}
