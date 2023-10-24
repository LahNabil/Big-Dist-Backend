package BigDistV2.group.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BigDistV2.group.backend.auth.AuthenticationRequest;
import BigDistV2.group.backend.auth.AuthenticationResponse;
import BigDistV2.group.backend.auth.AuthenticationService;
import BigDistV2.group.backend.auth.RegisterRequest;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173/")
public class Controller {
	
	@Autowired
	private AuthenticationService service;
	
	@PostMapping("/register")
	  public ResponseEntity<AuthenticationResponse> register(
	      @RequestBody RegisterRequest request
	  ) {
	    return ResponseEntity.ok(service.register(request));
	  }
	@PostMapping("/authenticate")
	  public ResponseEntity<AuthenticationResponse> authenticate(
	      @RequestBody AuthenticationRequest request
	  ) {
	    return ResponseEntity.ok(service.authenticate(request));
	  }
	
	
	
	 
    
    /*
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsersService userService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticateUser(@RequestBody OurUser ourUser) {
    	return userService.authenticate(ourUser);
    }
    

    @GetMapping("/")
    public String goH0me(){
        return "Thisn is publickly accesible withing needing authentication ";
    }
    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUSer(@RequestBody OurUser ourUser){
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        OurUser result = ourUserRepository.save(ourUser);
        if (result.getId() > 0){
            return ResponseEntity.ok("USer Was Saved");
        }
        return ResponseEntity.status(404).body("Error, USer Not Saved");
    }
    
    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUSers(){
        return ResponseEntity.ok(ourUserRepository.findAll());
    }
    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails(){
        return ResponseEntity.ok(ourUserRepository.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
    */
}
