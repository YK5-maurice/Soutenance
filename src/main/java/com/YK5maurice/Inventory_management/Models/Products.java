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
   // private Double quantity;
    private Double price;
    private Double reorder; //( Seuil de réapprovisionnement )
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

    public Products() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getReorder() {
        return reorder;
    }

    public void setReorder(Double reorder) {
        this.reorder = reorder;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public List<Product_Stocks> getProuct_stockList() {
        return prouct_stockList;
    }

    public void setProuct_stockList(List<Product_Stocks> prouct_stockList) {
        this.prouct_stockList = prouct_stockList;
    }

    public List<Supply_Products> getSupply_productsList() {
        return supply_productsList;
    }

    public void setSupply_productsList(List<Supply_Products> supply_productsList) {
        this.supply_productsList = supply_productsList;
    }

    public List<Request_Products> getRequest_productsList() {
        return request_productsList;
    }

    public void setRequest_productsList(List<Request_Products> request_productsList) {
        this.request_productsList = request_productsList;
    }
}