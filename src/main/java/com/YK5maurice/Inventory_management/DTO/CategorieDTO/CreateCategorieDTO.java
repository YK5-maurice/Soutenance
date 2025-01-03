package com.YK5maurice.Inventory_management.DTO.CategorieDTO;

import java.time.ZonedDateTime;

public class CreateCategorieDTO {

    private String name;
    private String description;


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
}
