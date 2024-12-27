package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.Config.ConfigurationSecurity;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.CreateUsersDTO;
import com.YK5maurice.Inventory_management.DTO.UsersDTO.UserDTO;
import com.YK5maurice.Inventory_management.Models.EnumTypeRole;
import com.YK5maurice.Inventory_management.Models.Roles;
import com.YK5maurice.Inventory_management.Models.Users;
import com.YK5maurice.Inventory_management.Repository.UsersRepo;
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

    public UsersService(UsersRepo usersRepo,RolesService rolesService) {
        this.usersRepo = usersRepo;
        this.rolesService=rolesService;
    }
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

//    // obtenir la liste de tous les utilisateur
//    public List<Users> getUsers(){
//        return usersRepo.findAll();
//    }
//
//
//    // obtenir un utilisateur par son id
//    public Users getUserById(Long id){
//        return usersRepo.findById(id).orElseThrow(()->new UsernameNotFoundException("utilisateur avec id "+id+" pas trouver"));
//    }

//    // creer un utililisateur
//    public void createUsuer(CreateUsersDTO createUsersDTO){
//
//        //recuperation de l'utilisateur et role du UserDTO
//        Users users = UserDTO_TO_user(createUsersDTO.getUsers());
//        int indiceRole = createUsersDTO.getIndiceRole();
//
//        //choix du role en fonction de son indice (ex:1->ROLE_ADMIN)
//        EnumTypeRole enumTypeRole = EnumTypeRole.values()[indiceRole];
//        System.out.println(enumTypeRole);
//        // creer un objet role car user utilise un objet role et non les enum directement
//        Roles roles = rolesService.getRoleByName(enumTypeRole.name());
//        System.out.println(roles.getDescription());
//        //verifie si le mail existe pas
//        if (usersRepo.existsByEmail(users.getEmail())) {
//            throw new RuntimeException("Email already exists");
//        }
//        //crypter le password
//        String password_encrypt= ConfigurationSecurity.bCryptPasswordEncoder().encode(users.getPassword());
//        //ajout du passwordCrypte et du role a users
//        users.setPassword(password_encrypt);
//        users.setRole(roles);
//        System.out.println(users.getRole());
//        usersRepo.save(users);
//    }

    //obtenir un utilisateur par son username
    public Users findByUsername(String username) {
        return usersRepo.findByUsername(username)
                .orElse(null); // Retourne `null` si aucun utilisateur n'est trouvé.
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
