
package com.YK5maurice.Inventory_management.Controller;

import com.YK5maurice.Inventory_management.Config.JwtUtil;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.UserLoginDTO;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.CreateUsersDTO;
import com.YK5maurice.Inventory_management.Models.Users;
import com.YK5maurice.Inventory_management.Services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UsersController {


    private  final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsersService usersService;
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersController(UsersService usersService, AuthenticationManager authenticationManager,JwtUtil jwtUtil, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersService = usersService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil=jwtUtil;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;

    }


// methode login(Authencate user)
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody UserLoginDTO loginRquest) {
    try {

        if (loginRquest == null ||
                loginRquest.getUsername() == null ||
                loginRquest.getPassword() == null ||
                loginRquest.getRole() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tous les champs sont obligatoires.");
        }

        // Récupération de l'utilisateur
        UserDetails user = usersService.loadUserByUsername(loginRquest.getUsername());

        // Vérification du mot de passe
        if (!bCryptPasswordEncoder.matches(loginRquest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect.");
        }

        // Vérification du rôle
        boolean roleExists = user.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(loginRquest.getRole()));
        if (!roleExists) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Rôle incorrect.");
        }

        // Authentification manuelle
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                loginRquest.getUsername(), loginRquest.getPassword(), user.getAuthorities());
        authenticationManager.authenticate(authentication);
System.out.println(authentication);
        // Génération du token
        String token = jwtUtil.generateToken(loginRquest.getUsername(), loginRquest.getRole());

        return ResponseEntity.ok(token);

    } catch (UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect.");
    } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue : " + ex.getMessage());
    }
}


// methode pour creer un user
    @PostMapping("/createUser")
    public ResponseEntity<Users> insertionUser(@RequestBody CreateUsersDTO createUsersDTO){
        Users users = this.usersService.createUsuer(createUsersDTO);
        return ResponseEntity.ok(users);
    }


//    // obtenir la liste des users
//    @GetMapping("/userslisteaa")
//    public List<Users> getUsers(){
//        return usersService.getUsers();
//    }
//
//    //obtenir users par son id
//    @GetMapping("/users")
//    public Users getUserById(@RequestParam Long Id){
//        return usersService.getUserById(Id);
//    }
//
//    // create users
//    @PostMapping("/usersliste")
//    public ResponseEntity<String> createUser(@RequestBody CreateUsersDTO usersDTO) {
//        try {
//
//
//            usersService.createUsuer(usersDTO);
//            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
}