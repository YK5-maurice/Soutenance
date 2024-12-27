package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "categories")
@Getter
@Setter
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date created_at;
    private Date update_at;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> productList;
}
