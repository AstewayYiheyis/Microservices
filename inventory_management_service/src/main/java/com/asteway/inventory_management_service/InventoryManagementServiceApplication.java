package com.asteway.inventory_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InventoryManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryManagementServiceApplication.class, args);
	}

}
