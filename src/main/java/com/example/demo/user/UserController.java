package com.example.demo.user;

import com.example.demo.product.Product;
import com.example.demo.product.ProductDTO;
import com.example.demo.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    @Autowired
    private ProductService productService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try{
            return ResponseEntity.ok(service.login(loginDTO));
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(productService.getProducts(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        try{
            Product product = new Product(productDTO);
            return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getProducts/{userid}")
    public ResponseEntity<?> getProducts(@PathVariable Long userid){
        try{
            List<Product> products = productService.findProductsByUserId(userid);
            return ResponseEntity.ok(products);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
