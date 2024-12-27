package com.YK5maurice.Inventory_management.DTO.UsersDTO;

public class CreateUsersDTO {
    private UserDTO users; // Remplacement de Users par UsersDTO
    private int indiceRole;

    // Getters et setters
    public UserDTO getUsers() {
        return users;
    }

    public void setUsers(UserDTO users) {
        this.users = users;
    }

    public int getIndiceRole() {
        return indiceRole;
    }

    public void setIndiceRole(int indiceRole) {
        this.indiceRole = indiceRole;
    }
}
