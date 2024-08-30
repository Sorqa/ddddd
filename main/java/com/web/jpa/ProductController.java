package com.web.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
@RestController //자동으로 respnosebody
@RequestMapping("/prod")
public class ProductController {
    @Autowired
    private ProductRepository prodRep;
    @GetMapping("")
    public String index() {
        return "JPA CRUD Controller";
    }

    @GetMapping("/add")
    public Product add() {
        Product p = new Product();
        p.setName("Grandeur");
        p.setPrice(4000);
        p.setDescription("Made In Hyundai");
        prodRep.save(p);
        Product savedProd = prodRep.save(p);
        return savedProd;
    }

    @GetMapping("/list")
    public List<Product> list() {
        return prodRep.findAll();
    }

    @GetMapping("/byid/{id}")
    public Product byid(@PathVariable int id) {
        Optional<Product> op =prodRep.findById(id);
        if(op.isPresent()){
            return op.get();
        }else{
            return null;
        }
    }

    @GetMapping("/update/{id}/{price}")
    public Product update(@PathVariable int id, @PathVariable int price) {
        Product p = new Product();
        p.setId(id);
        p.setPrice(price);
        return prodRep.save(p);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        prodRep.deleteById(id);
        return "Product deleted";
    }
}
