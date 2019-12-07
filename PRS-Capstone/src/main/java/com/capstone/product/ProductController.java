package com.capstone.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capstone.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	public ProductRepository productRepo;
	
	@GetMapping()
	public JsonResponse getAll() {
		return JsonResponse.getInstance(productRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<Product> p = productRepo.findById(id);
			if(p==null) 
				return JsonResponse.getInstance("Id cannot be null");
			if(!p.isPresent()) 
				return JsonResponse.getInstance("Product not found");
			return JsonResponse.getInstance(p.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	private JsonResponse save(Product product) {
		try {
			return JsonResponse.getInstance(productRepo.save(product));
		} catch (IllegalArgumentException iae) {
			return JsonResponse.getInstance("Product cannot be null");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@PostMapping()
	public JsonResponse insert(@RequestBody Product product) {
		try {
			return save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/{id]")
	public JsonResponse update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			if(id != product.getId()) {
				return JsonResponse.getInstance("Id does not exist");
			}
			return save(product);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			Optional<Product> p = productRepo.findById(id);
			if(id==null)
				return JsonResponse.getInstance("Id cannot be null");
			if(!p.isPresent()) 
				return JsonResponse.getInstance("Product not found");
			productRepo.deleteById(p.get().getId());
			return JsonResponse.getInstance(p.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
}