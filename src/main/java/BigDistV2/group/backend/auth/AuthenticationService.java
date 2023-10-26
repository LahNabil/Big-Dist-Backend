package BigDistV2.group.backend.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BigDistV2.group.backend.configuration.JwtService;
import BigDistV2.group.backend.model.Role;
import BigDistV2.group.backend.model.Token;
import BigDistV2.group.backend.model.TokenType;
import BigDistV2.group.backend.model.User;
import BigDistV2.group.backend.repository.TokenRepository;
import BigDistV2.group.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    

    

    public AuthenticationResponse register(RegisterRequest request) {
    	
    	var user = User.builder()
    			.firstname(request.getFirstname())
    			.lastname(request.getLastname())
    			.email(request.getEmail())
    			.password(passwordEncoder.encode(request.getPassword()))
    			.role(Role.ADMIN)
    			.build();
    	
    	var saveUser = repository.save(user);
    	var jwtToken = jwtService.generateToken(user);
    	saveUserToken(saveUser, jwtToken);
    	return AuthenticationResponse.builder()
    			.token(jwtToken)
    			.build();
    	
    			
        
}
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
        tokenRepository.save(token);
      }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
    	return AuthenticationResponse.builder()
    			.token(jwtToken)
    			.build();
      }
    
    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
          return;
        validUserTokens.forEach(token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
      }
    			
    			
    	
    }
	