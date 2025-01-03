package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,Long> {
    Products findByName(String name);
}
