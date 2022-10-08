package toy.blog.be.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import toy.blog.be.service.UserService;
import toy.blog.be.user.dto.SignUpRequest;

@Tag(name = "post apis")
@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @Operation(summary = "sign up")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(SignUpRequest request) {
        userService.save(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        return ResponseEntity.ok().build();

        //return "redirect:/login";
    }
}
