package toy.blog.be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.userDetailsService(userService);
		
		//super.configure(auth);
	}

//	@Override
//	public void configure(WebSecurity web) throws Exception {
//
//		web.ignoring().antMatchers("/css/**", "js/**", "/img/**");
//
//		//super.configure(web);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests().antMatchers("/login", "/signup", "/user","/swagger-ui.html").permitAll(); // 누구든 접근 허용
//			.antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
//			.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
//			.anyRequest().authenticated() // 나머지는 권한 종류 상관 없이 권한 있어야 접근 가능
//			.and()
//			.formLogin().loginPage("/login").defaultSuccessUrl("/")
//			.and()
//			.logout().logoutSuccessUrl("/login").invalidateHttpSession(true);
		
		//super.configure(http);
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
