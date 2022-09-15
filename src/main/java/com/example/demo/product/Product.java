package com.example.demo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    private Long id;
    private Long userId;
    private String productName;
    private Long quantity;
    private Double price;

    public Product(ProductDTO productDTO){
        this.productName=productDTO.getProductName();
        this.userId=productDTO.getUserid();
        this.quantity=productDTO.getQuantity();
        this.price=productDTO.getPrice();
    }
}
