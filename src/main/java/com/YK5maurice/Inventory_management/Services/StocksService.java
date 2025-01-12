package com.YK5maurice.Inventory_management.Services;

import com.YK5maurice.Inventory_management.DTO.Stocks.CreateStocksDTO;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNoContent;
import com.YK5maurice.Inventory_management.Exceptions.ResourceNotFoundException;
import com.YK5maurice.Inventory_management.Exceptions.ValidationException;
import com.YK5maurice.Inventory_management.Models.Stocks;
import com.YK5maurice.Inventory_management.Repository.StocksRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StocksService {

    private final StocksRepo stocksRepo;

    public StocksService (StocksRepo stocksRepo){
        this.stocksRepo = stocksRepo;
    }

    //....methode get ALL stock
    public List<Stocks> getAllStocks(){

        List<Stocks> stocksList = this.stocksRepo.findAll();

        if (stocksList.isEmpty()){
            throw  new ResourceNoContent("la liste des stocks est vide");
        }
        return stocksList;
    }


    //....methode get stock by ID
    public Stocks getStockById(long id){
        if (id <= 0){
            throw new ValidationException("le l'id : "+id+" dois etre > 0 ");
        }
        return this.stocksRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("le stock avec l'id :"+id+" n'existe pas"));
    }

    //....methode save Stock
    public Stocks createStock(CreateStocksDTO createStocksDTO) {
        if (createStocksDTO == null) {
            throw new ValidationException("l'objet passer en parametre ne dois pas etre vide");
        }

        try {
            //ceation de lobjet stock
            Stocks stocks = new Stocks();

            stocks.setCreated_at(new Date());
            stocks.setCurrent_quantity(createStocksDTO.getCurrentQuantity());
            stocks.setReorder_level(createStocksDTO.getReorderLevel());

            return this.stocksRepo.save(stocks);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
