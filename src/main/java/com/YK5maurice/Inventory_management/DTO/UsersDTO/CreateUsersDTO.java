package com.YK5maurice.Inventory_management.DTO.UsersDTO;

public class CreateUsersDTO {
    private UserDTO users; // Remplacement de Users par UsersDTO
    private String RoleName;
    private String departmentName;
    // Getters et setters

    public UserDTO getUsers() {
        return users;
    }

    public void setUsers(UserDTO users) {
        this.users = users;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
