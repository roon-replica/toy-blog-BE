package toy.blog.be.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Getter
@Service
public class JasyptService {
    // @Value나 환경변수로부터 getProperty를 통해 복호화된 값을 얻을 수 있다고 함!

    @Value("${jasypt.test.encrypted}")
    private String property;

    public String getPasswordUsingEnvironment(Environment environment){
        return environment.getProperty("jasypt.test.encrypted");
    }
}
