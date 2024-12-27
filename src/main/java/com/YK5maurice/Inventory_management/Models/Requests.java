package com.YK5maurice.Inventory_management.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "requests")
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date request_date;
    @Enumerated(EnumType.STRING)
    private EnumRequestStatus requestStatus;
    private Double total_quantity;
    private Date created_at;
    private Date update_at;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @OneToMany(mappedBy = "requests",cascade = CascadeType.ALL)
    private List<Request_Products> requests_productList;
}
