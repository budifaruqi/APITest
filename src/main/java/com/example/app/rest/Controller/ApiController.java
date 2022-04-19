package com.example.app.rest.Controller;

import com.example.app.rest.Model.Product;
import com.example.app.rest.Repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value = "/products")
    public List<Product> getProduct(){
       return productRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveProduct(@RequestBody Product product){
        productRepo.save(product);
        return "Saved...";
    }

    @PutMapping(value = "/update/{id}")
    public String updateProduct(@PathVariable long id,@RequestBody Product product){
        Product updateProduct = productRepo.findById(id).get();
        updateProduct.setNama(product.getNama());
        updateProduct.setStock(product.getStock());
        updateProduct.setHarga(product.getHarga());
        productRepo.save(updateProduct);
        return "Updated...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String delProduct(@PathVariable long id){
        Product delProduct = productRepo.findById(id).get();
        productRepo.delete(delProduct);
        return "Delete Product with ID : "+id;
    }
}
