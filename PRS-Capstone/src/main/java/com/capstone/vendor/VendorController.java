package com.capstone.vendor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capstone.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepo;
	
	@GetMapping()
	public JsonResponse getAll() {
		return JsonResponse.getInstance(vendorRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	private JsonResponse save(Vendor vendor) {
		try {
			Vendor v = vendorRepo.save(vendor);
			return JsonResponse.getInstance(v);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@PostMapping()
	public JsonResponse insert(@RequestBody Vendor vendor) {
		return save(vendor);
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody Vendor vendor, @PathVariable Integer id) {
		return save(vendor);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			vendorRepo.deleteById(id);
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
}
