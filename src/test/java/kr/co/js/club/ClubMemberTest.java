package kr.co.js.club;

import kr.co.js.club.entity.ClubMember;
import kr.co.js.club.entity.ClubMemberRole;
import kr.co.js.club.repository.ClubMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ClubMemberTest {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   // @Test
    public void insertDummies() {
        IntStream.rangeClosed(1,100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user"+i+"@gmail.com")
                    .name("사용자"+i)
                    .fromSocial(false)
                    .password( passwordEncoder.encode("1111") )
                    .build();
            //default role
            clubMember.addMemberRole(ClubMemberRole.USER);
            if(i > 80){
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }
            if(i > 90){
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }
           clubMemberRepository.save(clubMember);
        });
    }
    @Test
    public void testRead() {
        Optional<ClubMember> result =
                clubMemberRepository.findByEmail("user95@gmail.com", false);
        System.out.println(result.get());
    }


}


