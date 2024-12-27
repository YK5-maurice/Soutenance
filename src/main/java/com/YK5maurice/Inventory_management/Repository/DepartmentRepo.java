package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Departments;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DepartmentRepo extends JpaRepository<Departments,Long> {
    Departments findByName(String name);
}
