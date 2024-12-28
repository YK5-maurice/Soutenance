package com.YK5maurice.Inventory_management.Controller;


import com.YK5maurice.Inventory_management.Services.Department_UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departmentUsers")
public class DepartmentUsersController {

    private final Department_UsersService departmentUsersService;

    public DepartmentUsersController(Department_UsersService departmentUsersService){
        this.departmentUsersService = departmentUsersService;
    }

    //.... methode pour insertion
    @PostMapping("/insert")
    public ResponseEntity<String> insertionDepartmentUser(@RequestParam long idUsers, @RequestParam long idDepartment){

        //appel de la methode insertDepartment_users de departmentUsersService
        String insertion = this.departmentUsersService.insertDepartment_users(idDepartment,idUsers);

        return ResponseEntity.ok(insertion);
    }


}
