package toy.blog.be.config;

import lombok.RequiredArgsConstructor;
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
import toy.blog.be.service.OAuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final OAuthService oAuthService;
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
                ).permitAll()
                .and()
                    .oauth2Login()
                        .defaultSuccessUrl("/oauth/loginInfo", true)
                        .userInfoEndpoint()
                            .userService(oAuthService);

        http.csrf().disable();
        http.headers().frameOptions().disable(); // to use H2

        // TO DO //
//        .antMatchers("/").hasRole("USER") // USER, ADMIN??? ?????? ??????
//			.antMatchers("/admin").hasRole("ADMIN") // ADMIN??? ?????? ??????
//			.anyRequest().authenticated() // ???????????? ?????? ?????? ?????? ?????? ?????? ????????? ?????? ??????
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
