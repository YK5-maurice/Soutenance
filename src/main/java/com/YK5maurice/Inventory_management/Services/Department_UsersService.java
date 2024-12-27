package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.Department_UsersDTO.Department_UsersServiceDTO;
import com.YK5maurice.Inventory_management.Models.Department_Users;
import com.YK5maurice.Inventory_management.Models.Departments;
import com.YK5maurice.Inventory_management.Models.Users;
import com.YK5maurice.Inventory_management.Repository.Department_UsersRepo;

import java.util.Date;
import java.util.Optional;

public class Department_UsersService {

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
