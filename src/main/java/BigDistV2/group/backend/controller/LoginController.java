package BigDistV2.group.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod; // Import RequestMethod

/*import BigDistV2.group.backend.configuration.PasswordEncoder;*/

import BigDistV2.group.backend.repository.DBUserRepository;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private DBUserRepository DbUserRepository;
    
    @GetMapping("/admin")
    public String admin() {
    	return "Hi admin";
    }

    /*@Autowired(required = true)
    PasswordEncoder passwordEncoder;
    

    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:8084", methods = RequestMethod.POST, allowedHeaders = "*") // Use RequestMethod.POST

     public DBUser addUser(@RequestBody DBUser Dbuser) {
        String hash = passwordEncoder.bCryptPasswordEncoder().encode(Dbuser.getPassword());
        Dbuser.setPassword(hash);
        return DbUserRepository.save(Dbuser);
    }
    */
}
