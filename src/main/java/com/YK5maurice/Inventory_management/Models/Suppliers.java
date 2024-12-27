package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "suppliers")
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contact_info;
    private String address;
    private Date created_at;
    private Date updated_at;
    @OneToMany(mappedBy = "suppliers",cascade = CascadeType.ALL)
    private List<Supplies> suppliersList;
}
