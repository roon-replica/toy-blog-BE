package toy.blog.be.controller;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Void> signup(SignUpRequest request) {
        userService.save(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        return ResponseEntity.ok().build();

        //return "redirect:/login";
    }
}
