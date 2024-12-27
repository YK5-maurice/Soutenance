package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.EnumTypeRole;
import com.YK5maurice.Inventory_management.Models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Roles,Long> {

    Roles findByName(EnumTypeRole name);
}
