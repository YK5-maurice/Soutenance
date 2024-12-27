package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "department_users")
public class Department_Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created_at;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departments department;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    public Department_Users(Long id, Date created_at, Departments department, Users users) {
        this.id = id;
        this.created_at = created_at;
        this.department = department;
        this.users = users;
    }
}
