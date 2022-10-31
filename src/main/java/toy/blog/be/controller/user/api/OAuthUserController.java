package toy.blog.be.controller.user.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import toy.blog.be.controller.user.dto.request.NicknameUpdateRequest;
import toy.blog.be.service.OAuthUserService;

import javax.validation.Valid;
import java.util.Map;

@Tag(name = "oauth apis")
@RequestMapping("/oauth")
@RequiredArgsConstructor
@RestController
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;

    @GetMapping("/loginInfo")
    public String oauthLoginInfo(Authentication authentication) {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        return attributes.toString();
    }

    @PostMapping("/update-nickname")
    public ResponseEntity<String> updateNickname(@Valid @RequestBody NicknameUpdateRequest nicknameUpdateRequest) {
        var id = oAuthUserService.updateNickname(
                nicknameUpdateRequest.getId(),
                nicknameUpdateRequest.getNickname()
        );
        return ResponseEntity.ok().body(id);
    }
}
