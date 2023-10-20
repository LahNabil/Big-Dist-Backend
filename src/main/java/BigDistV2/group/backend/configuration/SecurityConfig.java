package BigDistV2.group.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
    
	private CustomUserDetailsService userDetailsService;
	*/
	
	
	
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth->auth
				.requestMatchers("/","/user/save","/batteries").permitAll()
				.anyRequest().authenticated())
				.httpBasic(withDefaults())
				.formLogin(withDefaults())
				.csrf(AbstractHttpConfigurer::disable);
		
					
				
					
			
			
			return http.build();
			
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return new OurUserInfoUserDetailsService();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	/*
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
	*/
	
	
	
	
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
