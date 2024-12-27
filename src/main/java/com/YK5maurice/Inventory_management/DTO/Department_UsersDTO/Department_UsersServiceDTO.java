package com.YK5maurice.Inventory_management.DTO.Department_UsersDTO;

public class Department_UsersServiceDTO {


    //private Long departmentId; // Correspond à l'ID de la relation ManyToOne avec Departments.
    private String departmentName; // Optionnel, si vous souhaitez inclure le nom du département.
    //private Long userId; // Correspond à l'ID de la relation ManyToOne avec Users.
    private String userName; // Optionnel, si vous souhaitez inclure le nom de l'utilisateur.

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
