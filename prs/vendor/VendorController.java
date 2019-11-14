package com.maxTrainBootcamp.prs.vendor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.maxTrainBootcamp.prs.util.JsonResponse;

@CrossOrigin
@Controller
@RequestMapping(path="/vendors")
public class VendorController {

	@Autowired
	private VendorRepository vendorRepo;
	
	@GetMapping("/authenticate")
	public @ResponseBody JsonResponse authenticate(@RequestBody Vendor vendor) {
		String code = vendor.getCode();
		String name = vendor.getName();
		try {
			Vendor v = vendorRepo.findByCodeAndName(code, name);
			if(v == null) {
				return JsonResponse.getInstance("Vendor not found");
			}
			return JsonResponse.getInstance(v);
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	@GetMapping()
	public @ResponseBody JsonResponse getAll() {
		return JsonResponse.getInstance(vendorRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());		
		}
	}
	
	private JsonResponse save(Vendor vendor) {
		try {
			Vendor v = vendorRepo.save(vendor);
			return JsonResponse.getInstance(v);
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}

	@PostMapping()
	public @ResponseBody JsonResponse insert(@RequestBody Vendor vendor) {
		return save(vendor);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse update(@RequestBody Vendor vendor, @PathVariable Integer id) {
		return save(vendor);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse delete(@PathVariable Integer id) {
		try {
			Optional<Vendor> v = vendorRepo.findById(id);
			if(!v.isPresent()) {
				return JsonResponse.getInstance("Vendor not found");
			}
			vendorRepo.deleteById(id);
			return JsonResponse.getInstance(v.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
}