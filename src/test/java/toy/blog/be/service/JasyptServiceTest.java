package toy.blog.be.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JasyptServiceTest {
    @Autowired
    private JasyptService jasyptService;

    @Autowired
    private Environment environment;

    private static String plainText = "This is my message to be encrypted";

    @BeforeEach
    public void init() {
        //System.setProperty("jasypt.encryptor.password", "password"); // ??
    }

    @Test
    public void decryptUsingGetterTest() {
        assertEquals(plainText, jasyptService.getProperty());
    }

    @Test
    public void decryptUsingEnvironment() {
        assertEquals(plainText, jasyptService.getPasswordUsingEnvironment(environment));
    }

}