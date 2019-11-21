package com.maxTrainBootcamp.prs.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxTrainBootcamp.prs.util.JsonResponse;

@CrossOrigin //security built into the browsers, protects servers, keeps servers from talking to each other unless it has this annotation 
@Controller 
@RequestMapping(path="/users") // maps path to user 
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping("/authenticate")
	public @ResponseBody JsonResponse authenticate(@RequestBody User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		try {
			User u = userRepo.findByUsernameAndPassword(username, password);
			if(u == null) {
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u);
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	@GetMapping() // can also have a path inside parenthesis
	public @ResponseBody JsonResponse getAll() {
		return JsonResponse.getInstance(userRepo.findAll()); //collection of users is inserted into JsonResponse, 
	}
	
	@GetMapping("/{id}")
	public @ResponseBody JsonResponse get(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id); //find and pass id
			if(!u.isPresent()) { //if u is not present
				return JsonResponse.getInstance("User not found");
			}
			return JsonResponse.getInstance(u.get()); //returns the user instance 
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	private JsonResponse save(User user) {
		try {
			User u = userRepo.save(user);
			return JsonResponse.getInstance(u);
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	@PostMapping()
	public @ResponseBody JsonResponse Insert(@RequestBody User user) {
		return save(user);
	}
	
	@PutMapping("/{id}")
	public @ResponseBody JsonResponse Update(@RequestBody User user, @PathVariable Integer id) {
		return save(user);
	}
	
	@DeleteMapping("/{id}")
	public @ResponseBody JsonResponse delete(@PathVariable Integer id) {
		try {
			Optional<User> u = userRepo.findById(id);
			if(!u.isPresent()) {
				return JsonResponse.getInstance("User not found");
			}
			userRepo.deleteById(id);
			return JsonResponse.getInstance(u.get());
		} catch (Exception e) {
			return JsonResponse.getInstance(e.getMessage());
		}
	}
	
	
}
