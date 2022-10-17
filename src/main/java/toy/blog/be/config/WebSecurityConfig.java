package toy.blog.be.config;

import net.bytebuddy.build.Plugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .passwordEncoder(passwordEncoder()::encode)
                .username("user")
                .password("1234")
                .roles("ADMIN")
                .build();
        // for test
        return new InMemoryUserDetailsManager(user);
    }
    @Bean
    public SecurityFilterChain filterCHain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/login",
                        "/signup",
                        "/user",
                        "/swagger-ui.html"
                )
                .permitAll()
                .antMatchers(
                        "/css/**",
                        "/js/**",
                        "/img/**"
                ).permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable(); // to use H2

        // TO DO //
//        .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
//			.antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
//			.anyRequest().authenticated() // 나머지는 권한 종류 상관 없이 권한 있어야 접근 가능
//			.and()
//			.formLogin().loginPage("/login").defaultSuccessUrl("/")
//			.and()
//			.logout().logoutSuccessUrl("/login").invalidateHttpSession(true);
        // TO DO //

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//    }
}
