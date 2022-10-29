package toy.blog.be.controller.user.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import toy.blog.be.service.UserService;
import toy.blog.be.controller.user.dto.SignUpRequest;

import javax.validation.Valid;

@Slf4j
@Tag(name = "post apis")
@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;

    @Operation(summary = "sign up")
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody SignUpRequest request) {
        log.info("signup api called");

        userService.save(request.getEmail(), request.getPassword(), request.getConfirmPassword());
        return ResponseEntity.ok().build();

        //return "redirect:/login";
    }

}
