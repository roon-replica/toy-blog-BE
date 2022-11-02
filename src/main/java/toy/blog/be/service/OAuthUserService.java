package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.blog.be.repository.OAuthUserRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class OAuthUserService {
    private final OAuthUserRepository oAuthUserRepository;

    public boolean checkNicknameDuplicate(String nickname) {
        return oAuthUserRepository.existsByNickname(nickname);
    }

    @Transactional
    public String updateNickname(String id, String nickname) {
        var oAuthUserInfo = oAuthUserRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        oAuthUserInfo.updateNickname(nickname);
        return id;
    }
}
