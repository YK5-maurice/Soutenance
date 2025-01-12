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

    public Product_Stocks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Stocks getStock() {
        return stock;
    }

    public void setStock(Stocks stock) {
        this.stock = stock;
    }
}
