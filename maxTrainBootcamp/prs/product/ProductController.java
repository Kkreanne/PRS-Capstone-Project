package com.maxTrainBootcamp.prs.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxTrainBootcamp.prs.util.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping()
	public @ResponseBody JsonResponse getAll() {
		return JsonResponse.getInstance(productRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse get(@PathVariable Integer id) {
		try {
			if(id == null) return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Product> v = productRepo.findById(id);
			if(!v.isPresent()) return JsonResponse.getInstance("Product not found");
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	private JsonResponse save(Product product) {
		try {
			return JsonResponse.getInstance(productRepo.save(product));
		} catch (IllegalArgumentException e) {
			return JsonResponse.getInstance("Parameter product cannot be null");
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PostMapping()
	public @ResponseBody JsonResponse insert(@RequestBody Product product) {
		try {
			return save(product);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse update(@RequestBody Product product, @PathVariable Integer id) {
		try {
			if(id != product.getId()) { 
				return JsonResponse.getInstance("Parameter id does not exist");
			}
			return save(product);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse delete(@PathVariable Integer id) {
		try {
			if(id == null) return JsonResponse.getInstance("Parameter id cannot be null");
			Optional<Product> v = productRepo.findById(id);
			if(!v.isPresent()) return JsonResponse.getInstance("Product not found");
			productRepo.deleteById(v.get().getId());
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
}
