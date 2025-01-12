package com.YK5maurice.Inventory_management.DTO.Stocks;

public class CreateStocksDTO {

    private Double currentQuantity;
    private Double reorderLevel;

    // Getters et Setters
    public Double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(Double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }

    public Double getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Double reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
}
