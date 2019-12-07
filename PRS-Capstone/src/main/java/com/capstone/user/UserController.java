package com.capstone.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capstone.util.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/login")
	public JsonResponse login(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			User u = userRepo.findByUsernameAndPassword(username, password);
			if(u==null) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	@GetMapping()
	public JsonResponse getAll() {
		return JsonResponse.getInstance(userRepo.findAll());
	}
	
	@GetMapping("/{id}")
	public JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id);
			if(!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e.getMessage());
		}
	}
		
	private JsonResponse save(User user) {
		try {
			User u = userRepo.save(user);
			return JsonResponse.getInstance(u);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	@PostMapping
	public JsonResponse insert(@RequestBody User user) {
		return save(user);
	}
	
	@PutMapping("/{id}")
	public JsonResponse update(@RequestBody User user, @PathVariable Integer id) {
		return save(user);
	}
	
	@DeleteMapping("/{id}")
	public JsonResponse delete(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id);
			if(!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			userRepo.deleteById(id);
			return JsonResponse.getInstance(u.get());
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResponse.getInstance(e);
		}
	}
}
