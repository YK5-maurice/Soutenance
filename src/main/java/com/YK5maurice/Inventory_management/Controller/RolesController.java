package com.YK5maurice.Inventory_management.Controller;

import com.YK5maurice.Inventory_management.DTO.RoleDTO.RolesDTO;
import com.YK5maurice.Inventory_management.Models.Roles;
import com.YK5maurice.Inventory_management.Services.RolesService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
public class RolesController {
    RolesService rolesService;

    //constructeur
    public RolesController(RolesService rolesService){
        this.rolesService=rolesService;
    }


    //...methode get All roles
    @GetMapping("")
    public ResponseEntity<List<Roles>>getAllRoles(){
        List<Roles> rolesList= this.rolesService.getAllRoles();
        return ResponseEntity.ok(rolesList);
    }

    //...methode get RoleByName
    @GetMapping("/Name")
    public ResponseEntity<Roles>getRolesByName(@RequestParam String name){
        Roles roles= this.rolesService.getRoleByName(name);
        return ResponseEntity.ok(roles);
    }

    //....methode pour get un role
    @GetMapping("/id")
    public ResponseEntity<Roles> saveRole(@RequestParam long id){
        Roles roles = this.rolesService.getRoleById(id);
        return ResponseEntity.ok(roles);
    }

}
