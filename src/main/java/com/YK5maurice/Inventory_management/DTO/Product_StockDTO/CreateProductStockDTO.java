package com.YK5maurice.Inventory_management.DTO.Product_StockDTO;

public class CreateProductStockDTO {

    private Long idProduct;
    private Long idStock;

    public CreateProductStockDTO() {
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdStock() {
        return idStock;
    }

    public void setIdStock(Long idStock) {
        this.idStock = idStock;
    }
}
