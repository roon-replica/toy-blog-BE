package toy.blog.be.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import toy.blog.be.domain.value.Role;
import toy.blog.be.repository.UserRepository;
import toy.blog.be.service.UserService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("1234").roles(Role.USER.name());
        // auth.userDetailsService(userService);
        //super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login", "/signup", "/user", "/swagger-ui.html").permitAll(); // 누구든 접근 허용

        http.authorizeRequests().antMatchers("/css/**", "js/**", "/img/**").permitAll();

        http.csrf().disable();

        http.headers().frameOptions().disable();

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
