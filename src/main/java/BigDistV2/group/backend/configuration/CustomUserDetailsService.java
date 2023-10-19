package BigDistV2.group.backend.configuration;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import BigDistV2.group.backend.model.Roles;
import BigDistV2.group.backend.model.UserEntity;
import BigDistV2.group.backend.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username Not found"));
		return new User(user.getUsername(), user.getPassword(), null);
		/* mapRolesToAuthorities(user.getRoles())*/
	}
	
	/*private Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		*/
	

}
