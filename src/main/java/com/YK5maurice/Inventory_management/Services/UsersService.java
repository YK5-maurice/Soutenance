package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.Config.ConfigurationSecurity;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.CreateUsersDTO;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.UserDTO;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Departments;
import com.YK5maurice.Inventory_management.Models.EnumTypeRole;
import com.YK5maurice.Inventory_management.Models.Roles;
import com.YK5maurice.Inventory_management.Models.Users;
import com.YK5maurice.Inventory_management.Repository.UsersRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.*;


@Service
public class UsersService implements UserDetailsService {

    private final UsersRepo usersRepo;
    private final RolesService rolesService;
    private final DepartmentService departmentService;
    private final Department_UsersService departmentUsersService;

    public UsersService(UsersRepo usersRepo,RolesService rolesService, DepartmentService departmentService,
                        //L'annotation @Lazy indique à Spring de charger la dépendance uniquement lorsque cela est nécessaire, évitant ainsi le cycle
                        @Lazy Department_UsersService departmentUsersService) {
        this.usersRepo = usersRepo;
        this.rolesService=rolesService;
        this.departmentService = departmentService;
        this.departmentUsersService = departmentUsersService;
    }


    // .....methode pour les authentification
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        // Obtenir le rôle de l'utilisateur
        Roles role = users.getRole();
        //convertion du role en authorities pour pringframework security
//        // cas ou le user peut avoir plusieurs role (ou user a une collection de role)
//        Set<GrantedAuthority> authorities = users.getRole().Strim()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());
        // cas ou le user a un seul role
        Set<GrantedAuthority> authorities = Collections.singleton(
                new SimpleGrantedAuthority(role.getName().toString())
        );
        // Retourne un objet `UserDetails` de org.springframework.security.core.userdetails.User qui contient le username, password et les rôles (authorities)
        return new User(users.getName(), users.getPassword(), authorities);
    }


    // ...... methode pour recuperer un utilisateur par son nom
    public Users findByUsername(String username){
        if (username == null){
            throw new ValidationException("le nom de l'utilisateur ne doit pas etre null");
        }
        Users users = this.usersRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("utilisateur avec le nom : "+username+" n'existe pas"));

        return users;
    }


    //....methode obtenir un utilisateur par son id
    public Users getUserById(Long id){
        if (id <= 0){
            throw new ValidationException("le user avec l'id : "+id+" n'existe pas");
        }
        return usersRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("utilisateur avec id : "+id+" n'existe pas"));
    }


    //....methode pour creer un utililisateur
    public Users createUsuer(CreateUsersDTO createUsersDTO){
        //verifie que createUsersDTO est bien diferent de null
        if (createUsersDTO == null){
            throw new ValidationException("l'objet (createUsersDTO) ne doit pas etre vide");
        }

        //recuperation du departement par son contenu dans createUsersDTO
        Departments departments = this.departmentService.getDepartementByName(createUsersDTO.getDepartmentName());

        //recuperation du role par son nom contenu dans createUsersDTO
        Roles roles = this.rolesService.getRoleByName(createUsersDTO.getRoleName());

        //recuperation de UserDTO contenu dans createUsersDTO
        UserDTO userDTO = createUsersDTO.getUsers();

        // Vérifie si le nom d'utilisateur existe déjà
        if (usersRepo.findByUsername(userDTO.getUsername()).isPresent()) {
            // Option 1 : Retourner une réponse appropriée
            throw new ValidationException("Le nom d'utilisateur '" + userDTO.getUsername() + "' existe déjà");

            // Option 2 : Ne rien faire et retourner l'utilisateur existant (si besoin)
            // return usersRepo.findByUsername(userDTO.getUsername()).get();
        }


        //creation d'un new user pour l'insertion
        Users users = new Users();

        //initialisation des parametre de users
        users.setName(userDTO.getName());
        users.setEmail(userDTO.getEmail());
        users.setUsername(userDTO.getUsername());
       // users.setPassword(userDTO.getPassword());
        //crypter le password
        String password_encrypt= ConfigurationSecurity.bCryptPasswordEncoder().encode(userDTO.getPassword());
        //ajout du passwordCrypte
        users.setPassword(password_encrypt);
        users.setCreated_at(new Date());
        users.setStatus(userDTO.getStatus());
        users.setRole(roles);

        // Insertion dans la base de données
        Users savedUser = this.usersRepo.save(users);

        // Enregistrement dans department_users
        try {
            this.departmentUsersService.insertDepartment_users(departments.getId(), savedUser.getId());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'enregistrement dans department_users : "+departments.getId()+"____" + savedUser.getId());
        }

        return savedUser;
   }

//
//    //convert UserDTO TO user
//    public Users UserDTO_TO_user(UserDTO userDTO){
//
//        Users users = new Users();
//
//        if (userDTO == null){
//            return  null;
//        }
//        users.setName(userDTO.getName());
//        users.setEmail(userDTO.getEmail());
//        users.setUsername(userDTO.getUsername());
//        users.setPassword(userDTO.getPassword());
//        users.setCreated_at(new Date());
//        users.setStatus(userDTO.getStatus());
//
//        return users;
//    }

}
