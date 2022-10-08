package toy.blog.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import toy.blog.be.service.UserService;
import toy.blog.be.user.dto.UserInfoDto;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final UserService userService;
	
	@PostMapping("/user")
	public String signup(UserInfoDto infoDto) {
		userService.save(infoDto);
		
		return "redirect:/login";
	}
}
