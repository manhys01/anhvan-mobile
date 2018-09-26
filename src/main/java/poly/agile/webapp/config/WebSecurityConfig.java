package poly.agile.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import poly.agile.webapp.service.user.UserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/webfonts/**",
			"/images/**",
			"/",
			"/about",
			"/contact",
			"/products**",
			"/products/**",
			"/login",
			"/register"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN");
			
		http
			.csrf().disable().cors().disable()
			.formLogin().failureForwardUrl("/login?error")
			.defaultSuccessUrl("/").permitAll()
			.and()
			.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
			.and().rememberMe();
		
		http.exceptionHandling().accessDeniedPage("/403");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}
	
}
