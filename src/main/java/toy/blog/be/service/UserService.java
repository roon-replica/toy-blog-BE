package toy.blog.be.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import toy.blog.be.domain.UserInfo;
import toy.blog.be.repository.UserRepository;
import toy.blog.be.user.dto.UserInfoDto;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserInfo loadUserByUsername(String email) throws UsernameNotFoundException {
		
		return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email)) ;
	}
	
	public String save(UserInfoDto infoDto) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		infoDto.setPassword(encoder.encode(infoDto.getPassword()));

		return userRepository.save(UserInfo.builder()
			.email(infoDto.getEmail())
			.auth(infoDto.getAuth())
			.password(infoDto.getPassword()).build()).getCode();
	}
	
}
