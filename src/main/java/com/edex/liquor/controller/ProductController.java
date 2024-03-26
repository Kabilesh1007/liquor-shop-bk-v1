package com.edex.liquor.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.edex.liquor.model.Product;
import com.edex.liquor.repo.ProductRepo;

import jakarta.websocket.server.PathParam;


@RestController
@RequestMapping("/product")

public class ProductController {
	
	
	@Autowired
	private ProductRepo productRepo;

	
	@PostMapping("/set")
	public ResponseEntity<?> setProduct(@RequestBody Product product){
		Product savedEntity = productRepo.saveAndFlush(product);
		return ResponseEntity.status(HttpStatus.OK)
				.body(savedEntity);
	}
	
	@GetMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestParam int id){
		Product product =  productRepo.findById(id).get();
		productRepo.delete(product);
		return ResponseEntity.status(HttpStatus.OK)
				.body("Deleted");
	}

@GetMapping("/findbycatogries/{catogries}")
    public ResponseEntity<?> findBycatogries(@PathVariable String catogries) {
		System.out.println("===Category===="+catogries);
        List<Product> product= productRepo.findBycatogries(catogries);
        return ResponseEntity.status(HttpStatus.OK)
                .body(product);
}

	
	@GetMapping("/getfile")
    public ResponseEntity<?> getFile(){
        List<Product> products = productRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }
	
	@GetMapping("/findByPrice")
	public ResponseEntity<?> findByPrice(@RequestParam double price){
		List<Product> products = productRepo.findByPrice(price);
	
		return ResponseEntity.status(HttpStatus.OK)
				.body(products);
	}
	
	@GetMapping("/findBetweenPrice")
	public ResponseEntity<?> findBetweenPrice(@RequestParam double p1, @RequestParam double p2){
		List<Product> products = productRepo.findBetweenPrice(p1, p2);
	
		return ResponseEntity.status(HttpStatus.OK)
				.body(products);
	}

}
