package kr.co.js.club;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SpringPasswordTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncoding(){
        String password = "1111";
        String enPw = passwordEncoder.encode(password);

        System.out.println("인코딩된 1111:" + enPw);

        System.out.println("비교:" + passwordEncoder.matches(password,enPw));


    }
}
