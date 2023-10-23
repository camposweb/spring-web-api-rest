package dio.springwebapirest.user;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping()
	public ResponseEntity<UserModel> create(@RequestBody UserModel userModel) {
		var user = this.userRepository.save(userModel);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping()
	public List<UserModel> list() {
		var users = this.userRepository.findAll();
		return users;
	}
	
	@GetMapping("/{username}")
	public ResponseEntity getByName(@PathVariable String username) {
		var user = this.userRepository.findByUsername(username);
		
		if (user == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário não encontrado");
		}

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") UUID id) {
		userRepository.deleteById(id);
	}
}
