package com.YK5maurice.Inventory_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class InventoryManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementApplication.class, args);
	}
}