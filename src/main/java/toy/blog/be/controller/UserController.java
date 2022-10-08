package toy.blog.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import toy.blog.be.service.UserService;
import toy.blog.be.user.dto.SignUpRequest;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(SignUpRequest request) {
        String id = userService.save(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        return id;

        //return "redirect:/login";
    }
}
