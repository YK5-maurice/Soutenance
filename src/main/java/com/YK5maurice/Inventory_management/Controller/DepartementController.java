package com.YK5maurice.Inventory_management.Controller;


import com.YK5maurice.Inventory_management.Models.Departments;
import com.YK5maurice.Inventory_management.Services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("departement")
public class DepartementController {

     DepartmentService departmentService;

    public DepartementController(DepartmentService departmentService){
        this.departmentService=departmentService;
    }

    //...methode pour get All departements
    @GetMapping("")
    public ResponseEntity<List<Departments>> getAllDepartement(){
        List<Departments> departmentsList = this.departmentService.getAllDepartement();
        return ResponseEntity.ok(departmentsList);
    }

    //...methode pour get departement by id
    @GetMapping("/id")
    public ResponseEntity<Departments> getDepartementById(@RequestParam long id){
        Departments departments = departmentService.getDepartementById(id);
        return ResponseEntity.ok(departments);
    }

    //...methode pour get departement by Name
    @GetMapping("/Name")
    public ResponseEntity<Departments> getDepartementByName(@RequestParam String name){
        Departments departments = this.departmentService.getDepartementByName(name);
        return  ResponseEntity.ok(departments);
    }


}
