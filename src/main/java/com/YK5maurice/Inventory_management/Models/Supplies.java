package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "supplies")
public class Supplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Date created_at;
    private Date updated_at;
    @OneToMany(mappedBy = "supplies",cascade = CascadeType.ALL)
    private List<Supply_Products> supply_productsList;
    @ManyToOne
    @JoinColumn(name = "suppliers_id")
    private Suppliers suppliers;
}