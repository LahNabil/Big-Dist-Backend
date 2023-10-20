package BigDistV2.group.backend.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import BigDistV2.group.backend.model.OurUser;
import BigDistV2.group.backend.repository.OurUserRepository;

@Configuration
public class OurUserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private OurUserRepository ourUserRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<OurUser> user = ourUserRepository.findByEmail(username);
        return user.map(OurUserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User Does Not Exist"));
    }

}
