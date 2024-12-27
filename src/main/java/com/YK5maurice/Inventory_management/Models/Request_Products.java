package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "request_products")
public class Request_Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantity;
    private Date created_at;
    @Enumerated(EnumType.STRING)
    private EnumRequestType RquestType;
    @ManyToOne
    @JoinColumn(name = "requests_id")
    private Requests requests;
    @ManyToOne
    @JoinColumn(name = "products_id")
    private Products product;
}
