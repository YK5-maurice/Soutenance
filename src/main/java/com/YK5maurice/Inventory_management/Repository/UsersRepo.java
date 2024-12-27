package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepo  extends JpaRepository<Users,Long> {
    Optional<Users> findByUsername(String username);

    Boolean existsByEmail(String email);
}
