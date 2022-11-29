package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.blog.be.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Transactional
    public String updateNickname(String id, String nickname) {
        var oAuthUserInfo = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        oAuthUserInfo.updateNickname(nickname);
        return id;
    }
}
