package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "supply_products")
public class Supply_Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private Date supply_date;
    private Date created_at;
    @ManyToOne
    @JoinColumn(name = "supply_id")
    private Supplies supplies;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;
}
