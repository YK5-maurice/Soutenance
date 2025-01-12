package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Product_Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_StockRepo extends JpaRepository<Product_Stocks,Long> {
}
