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

    public Stocks() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCurrent_quantity() {
        return current_quantity;
    }

    public void setCurrent_quantity(Double current_quantity) {
        this.current_quantity = current_quantity;
    }

    public Double getReorder_level() {
        return reorder_level;
    }

    public void setReorder_level(Double reorder_level) {
        this.reorder_level = reorder_level;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public List<Product_Stocks> getProduct_stockList() {
        return product_stockList;
    }

    public void setProduct_stockList(List<Product_Stocks> product_stockList) {
        this.product_stockList = product_stockList;
    }
}
