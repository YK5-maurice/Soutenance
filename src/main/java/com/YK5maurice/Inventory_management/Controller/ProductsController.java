package com.YK5maurice.Inventory_management.Controller;


import com.YK5maurice.Inventory_management.DTO.ProductsDTO.CreateProducteDTO;
import com.YK5maurice.Inventory_management.Models.Products;
import com.YK5maurice.Inventory_management.Services.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductsController {

    private ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService = productsService;
    }


    @PostMapping("/createProduct")
    public ResponseEntity<Products> CreateProduct(@RequestBody CreateProducteDTO createProducteDTO){
        Products products = this.productsService.CreateProducts(createProducteDTO);

        return ResponseEntity.ok(products);
    }
}
