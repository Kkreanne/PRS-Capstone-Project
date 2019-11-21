package com.maxTrainBootcamp.prs.request;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.maxTrainBootcamp.prs.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {
	private static final String REQUEST_STATUS_NEW = "NEW";
	private static final String REQUEST_STATUS_EDIT = "EDIT";
	private static final String REQUEST_STATUS_REVIEW = "REVIEW";
	private static final String REQUEST_STATUS_APPROVED = "APPROVED";
	private static final String REQUEST_STATUS_REJECTED = "REJECTED";
	
	@Autowired
	private RequestRepository requestRepo;
	
	@GetMapping()
	public JsonResponse getAll() {
		try {
			return JsonResponse.getInstance(requestRepo.findAll());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
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
	
	private JsonResponse save(Request request) {
		try {
			return JsonResponse.getInstance(requestRepo.save(request));
		} catch (IllegalArgumentException iae) {
			return JsonResponse.getInstance("Request cannot be null");
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PostMapping()
	public JsonResponse add(Request request) {
		try {
			request.setStatus(REQUEST_STATUS_NEW);
			request.setTotal(0);
			return save(request);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@GetMapping("/reviews/{userId}")
	public JsonResponse getRequestWithStatusOfReview(@PathVariable Integer userId) {
		try {
			if(userId==null) return JsonResponse.getInstance("UserId cannot be null");
			Iterable<Request> requests
				= requestRepo.getRequestByStatusAndUserIdNot(REQUEST_STATUS_REVIEW, userId);
			return JsonResponse.getInstance(requests);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	private JsonResponse setRequestStatus(Request request, String status) {
		try {
			request.setStatus(status);
			return save(request);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/review/{id}")
	public JsonResponse review(Request request, @PathVariable Integer id) {
		try {
			if(id != request.getId()) { 
				return JsonResponse.getInstance("Does not match request.");
			}
			request.setSubmittedDate(new Date(System.currentTimeMillis()));
			if(request.getTotal() <= 50) {
				return setRequestStatus(request, REQUEST_STATUS_APPROVED);
			}
			return setRequestStatus(request, REQUEST_STATUS_REVIEW);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
		
	@PutMapping("/approve/{id}")
	public JsonResponse approve(Request request, @PathVariable Integer id) {
		try {
			if(id != request.getId()) return JsonResponse.getInstance("Does not match request.");
			return setRequestStatus(request, REQUEST_STATUS_APPROVED);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/reject/{id}")
	public JsonResponse reject(Request request, @PathVariable Integer id) {
		try {
			if(id != request.getId()) return JsonResponse.getInstance("Does not match request.");
			return setRequestStatus(request, REQUEST_STATUS_REJECTED);
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(Request request, @PathVariable Integer id) {
		try {
			if(id==null) return JsonResponse.getInstance("Cannot be null");
			Optional<Request> r = requestRepo.findById(id);
			if(!r.isPresent()) return JsonResponse.getInstance("Request not found");
			return JsonResponse.getInstance(r.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			if(id==null) return JsonResponse.getInstance("Cannot be null");
			Optional<Request> r = requestRepo.findById(id);
			if(!r.isPresent()) return JsonResponse.getInstance("Request not found");
			return JsonResponse.getInstance(r.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e);
		}
	
	}
	
}
