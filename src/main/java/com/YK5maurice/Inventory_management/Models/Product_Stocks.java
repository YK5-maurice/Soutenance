package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter @Getter
@Table(name = "product_stocks")
public class Product_Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created_at;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
    @ManyToOne
    @JoinColumn(name = "stock_id")
    private Stocks stock;
}
