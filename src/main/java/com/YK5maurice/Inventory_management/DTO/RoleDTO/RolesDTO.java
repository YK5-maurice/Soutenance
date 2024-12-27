package com.YK5maurice.Inventory_management.DTO.RoleDTO;

import com.YK5maurice.Inventory_management.Models.EnumTypeRole;

public class RolesDTO {

    EnumTypeRole name;
    String description;

    public EnumTypeRole getName() {
        return name;
    }

    public void setName(EnumTypeRole name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
