package toy.blog.be.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@TestConfiguration()
class JasyptConfigTest {

    private StringEncryptor encryptor;

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JasyptConfig.class);
        encryptor = context.getBean(StringEncryptor.class);
    }

    @Test
    void env_var_test () {
        // when
        String encryptionKey = System.getenv(JasyptConfig.ENV_JASYPT_KEY);

        // then
        assertThat(encryptionKey).isNotNull();
        assertThat(encryptionKey).isNotEmpty();
    }

    @Test
    void encryp_test () {
        // given
        String source = "암호화할 문자열";

        // when
        String encrypted = encryptor.encrypt(source);
        String decrypt = encryptor.decrypt(encrypted);

        System.out.println(encrypted);

        // then
        assertThat(encrypted).isNotNull();
        assertThat(encrypted).isNotEmpty();
        assertThat(decrypt).isEqualTo(source);
    }

}