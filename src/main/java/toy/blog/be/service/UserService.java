package toy.blog.be.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toy.blog.be.domain.UserInfo;
import toy.blog.be.domain.value.Role;
import toy.blog.be.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfo loadUserByUsername(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id));
    }

    public String save(String email, String password, String confirmPassword) {
        if(isPasswordCoincide(password,confirmPassword) == false){
            throw new IllegalArgumentException("password and confirmPassword are different");
        }

        return userRepository.save(UserInfo.builder()
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .auth(Role.USER)
                        .build())
                .getId();
    }

    private boolean isPasswordCoincide(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
