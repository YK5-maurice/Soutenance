package com.YK5maurice.Inventory_management.Repository;

import com.YK5maurice.Inventory_management.Models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StocksRepo extends JpaRepository<Stocks,Long> {

    //Stocks findByName(String name);
}
