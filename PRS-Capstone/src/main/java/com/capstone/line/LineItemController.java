package com.capstone.line;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capstone.request.Request;
import com.capstone.request.RequestController;
import com.capstone.request.RequestRepository;
import com.capstone.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/line-items")
public class LineItemController {

	@Autowired
	private LineItemRepository lineItemRepo;
	@Autowired
	private RequestRepository requestRepo;
	
	private void calculation(int requestId) throws Exception {
		Optional<Request> r = requestRepo.findById(requestId);
		if(!r.isPresent()) {
			throw new Exception("Cannot find request with id " + requestId);
		}
		Request request = r.get();
		Iterable<LineItem> lines = lineItemRepo.getLineItemByRequestId(request.getId());
		double total = 0;
		for(LineItem line : lines) {
			total += line.getQuantity() * line.getProduct().getPrice();
		}
		request.setTotal(total);
		request.setStatus(RequestController.REQUEST_STATUS_EDIT);
		requestRepo.save(request);
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<LineItem> l = lineItemRepo.findById(id);
			if(id==null) return JsonResponse.getInstance("The id cannot be null.");
			if(!l.isPresent()) return JsonResponse.getInstance("Line Item not found.");
			return JsonResponse.getInstance(l.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	private JsonResponse save(LineItem lineItem) {
		try {
			return JsonResponse.getInstance(lineItemRepo.saveAndFlush(lineItem));
		} catch (IllegalArgumentException iae) {
			return JsonResponse.getInstance(iae);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@PostMapping()
	public JsonResponse insert(@RequestBody LineItem lineItem) {
		try {
			JsonResponse jr = save(lineItem);
			calculation(lineItem.getRequest().getId());
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody LineItem lineItem, @PathVariable Integer id) {
		try {
			if(id != lineItem.getId()) { 
				return JsonResponse.getInstance("Id doesn't match.");
			}
			JsonResponse jr = save(lineItem);
			calculation(lineItem.getRequest().getId());
			return jr;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			if(id==null) return JsonResponse.getInstance("Cannot be null.");
			Optional<LineItem> l = lineItemRepo.findById(id);
			if(!l.isPresent()) return JsonResponse.getInstance("Line Item not found.");
			lineItemRepo.deleteById(l.get().getId());
			lineItemRepo.flush();
			calculation(l.get().getRequest().getId());
			return JsonResponse.getInstance(l.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
}