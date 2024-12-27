package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Setter @Getter
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double quantity;
    private Double price;
    private Double reorder;
    private Date created_at;
    private Date update_at;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Product_Stocks> prouct_stockList;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Request_Products> request_productsList;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<Supply_Products> supply_productsList;
}
