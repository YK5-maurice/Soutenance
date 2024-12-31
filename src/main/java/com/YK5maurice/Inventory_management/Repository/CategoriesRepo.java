package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepo extends JpaRepository<Categories,Long> {
    Categories findByName(String name);
}
