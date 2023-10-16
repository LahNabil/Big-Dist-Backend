/* package BigDistV2.group.backend.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BigDistV2.group.backend.model.DBUser;
import BigDistV2.group.backend.repository.DBUserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
	
	@Autowired
	private DBUserRepository dbUserRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		DBUser user = dbUserRepository.findByUsername(username);
		if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		 return User.builder()
		            .username(user.getUsername())
		            .password(user.getPassword())
		            .authorities(authorities)
		            .build();
	}

	

}*/
