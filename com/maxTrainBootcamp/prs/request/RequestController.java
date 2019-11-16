package com.maxTrainBootcamp.prs.request;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maxTrainBootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	private RequestRepository requestRepo;
	
	@GetMapping()
	public JsonResponse getAll() {
		return JsonResponse.getInstance(requestRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			if(id == null) return JsonResponse.getInstance("Cannot be null");
			Optional<Request> r = requestRepo.findById(id);
			if(!r.isPresent()) return JsonResponse.getInstance("Request not found");
			return JsonResponse.getInstance(r.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	private 
	
}
