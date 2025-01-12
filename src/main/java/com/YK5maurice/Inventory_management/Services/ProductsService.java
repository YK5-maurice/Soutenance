package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.Product_StockDTO.CreateProductStockDTO;
import com.YK5maurice.Inventory_management.DTO.ProductsDTO.CreateProducteDTO;
import com.YK5maurice.Inventory_management.DTO.Stocks.CreateStocksDTO;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Categories;
import com.YK5maurice.Inventory_management.Models.Products;
import com.YK5maurice.Inventory_management.Models.Stocks;
import com.YK5maurice.Inventory_management.Repository.ProductsRepo;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductsService {
    private final ProductsRepo productsRepo;
    private final CategoriesService categoriesService;
    private final StocksService stocksService;
    private final Product_StockService productStockService;

    public ProductsService(ProductsRepo productsRepo, @Lazy CategoriesService categoriesService,@Lazy StocksService stocksService,@Lazy Product_StockService productStockService){
        this.productsRepo = productsRepo;
        this.categoriesService = categoriesService;
        this.stocksService = stocksService;
        this.productStockService = productStockService;
    }

    //.....create et products
    public Products CreateProducts(CreateProducteDTO createProducteDTO){
        if (createProducteDTO == null){
            throw new ValidationException("l'objet passer en parametre ne dois pas etre vide");
        }
         //recuperation de la categorie du produit par son id
        Categories categories = this.categoriesService.getCategoriesById(createProducteDTO.getCategoryId());

        // creation d'un objet dto 'createStocksDTO' de la class stock car l'enregistrement se fait par un objet dto
        CreateStocksDTO createStocksDTO = new CreateStocksDTO();

        createStocksDTO.setCurrentQuantity(createProducteDTO.getQuantity());
        createStocksDTO.setReorderLevel(createProducteDTO.getReorder());
        //enregigistrement dans la table Stocks
        Stocks stocks = this.stocksService.createStock(createStocksDTO);

        //enregistrement dans la table Produit
        Products products = new Products();

        products.setCreated_at(new Date());
        products.setName(createProducteDTO.getName());
        products.setPrice(createProducteDTO.getPrice());
        products.setCategory(categories);
        products.setDescription(createProducteDTO.getDescription());
        products.setReorder(createProducteDTO.getReorder());

        Products products1 = this.productsRepo.save(products);

        //enregistrement dans la table Product_Stock
        CreateProductStockDTO createProductStockDTO = new CreateProductStockDTO();
        createProductStockDTO.setIdProduct(products1.getId());
        createProductStockDTO.setIdStock(stocks.getId());

        this.productStockService.CreateProductStock(createProductStockDTO);

        return products1;
    }


// .... methode get productById
    public Products getProductById(long id) {
        if (id <= 0){
            throw new ValidationException("l'id : "+id+" doit etre > 0");
        }
        return this.productsRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("le produit avec l'id : "+id+" n'exist pas"));
    }
}
