package com.YK5maurice.Inventory_management.DTO.ProductsDTO;

public class CreateProducteDTO {

    private String name;
    private String description;
    private Double price;
    private Double reorder; // Seuil de réapprovisionnement
    private Long categoryId; // ID de la catégorie associée
    private Double quantity;

    // Getters et Setters
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
