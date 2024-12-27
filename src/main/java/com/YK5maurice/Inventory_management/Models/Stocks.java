package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "stocks")
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double current_quantity;
    private Double reorder_level;
    private Date created_at;
    private Date last_updated;
    @OneToMany(mappedBy = "stock",cascade = CascadeType.ALL)
    List<Product_Stocks> product_stockList;
}
