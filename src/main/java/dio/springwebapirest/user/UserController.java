package dio.springwebapirest.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	public ResponseEntity create(@RequestBody UserModel userModel) {
		var user = this.userRepository.save(userModel);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("")
	public List<UserModel> list() {
		var users = this.userRepository.findAll();
		return users;
	}
	
	@GetMapping("/{username}")
	public UserModel getByName(@PathVariable("username") String username) {
		var user = this.userRepository.findByUsername(username);
		return user;
	}
}
