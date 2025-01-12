package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.Product_StockDTO.CreateProductStockDTO;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Product_Stocks;
import com.YK5maurice.Inventory_management.Models.Products;
import com.YK5maurice.Inventory_management.Models.Stocks;
import com.YK5maurice.Inventory_management.Repository.Product_StockRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Product_StockService {

    private final Product_StockRepo productStockRepo;
    private final StocksService stocksService;
    private final ProductsService productsService;

    public Product_StockService(Product_StockRepo productStockRepo, @Lazy StocksService stocksService,@Lazy ProductsService productsService){
        this.productStockRepo = productStockRepo;
        this.stocksService = stocksService;
        this.productsService = productsService;
    }

    ///...methode creat product_stock
    public Product_Stocks CreateProductStock(CreateProductStockDTO createProductStockDTO){
        if (createProductStockDTO == null){
            throw new ValidationException("l'objet : createProductStockDTO ne dois pas etre vide");
        }

        try {
            // recuper l'objet stock
            Stocks stocks = this.stocksService.getStockById(createProductStockDTO.getIdStock());
            Products products = this.productsService.getProductById(createProductStockDTO.getIdProduct());

            //creation et initialisation d'un objet Product_Stocks
            Product_Stocks productStocks = new Product_Stocks();

            productStocks.setStock(stocks);
            productStocks.setProduct(products);
            productStocks.setCreated_at(new Date());

            return this.productStockRepo.save(productStocks);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
