package BigDistV2.group.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	/*
	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.builder().username("admin").password("password").roles("ADMIN").build();
		return new InMemoryUserDetailsManager(admin);
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder for password hashing
    }
    */
	private CustomUserDetailsService userDetailsService;
	
	
	
	
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable()
			.authorizeRequests()
			.requestMatchers("/batteries").permitAll()
			.and()
			.httpBasic();
			
			return http.build();
			
	}
	@Bean
	public UserDetailsService users() {
		UserDetails admin = User.builder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		UserDetails user = User.builder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
				
				
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
/*
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests()
		.requestMatchers("/batteries").permitAll()
		.requestMatchers("/admin").hasRole("ADMIN")
		.and()
		.formLogin();
		
	}
	
		 return http
				.csrf(csrf -> csrf.disable() )
				.authorizeRequests(authorizeRequests -> authorizeRequests {
					auth.requestMatchers("/batteries").permitAll();
					auth.requestMatchers("/admin").hasRole("ADMIN");
				})
				.httpBasic(Customizer.withDefaults())
				.build();
				
				
				
	}
	*/

}
