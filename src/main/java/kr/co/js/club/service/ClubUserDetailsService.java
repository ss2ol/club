package kr.co.js.club.service;

import kr.co.js.club.dto.ClubAuthMember;
import kr.co.js.club.entity.ClubMember;
import kr.co.js.club.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {
    private final ClubMemberRepository clubMemberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        log.info("loadByUsername" + username);

        ClubMember clubMember = clubMemberRepository.findByEmail(username, false).get();

        ClubAuthMember clubAuthMember = new ClubAuthMember(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE" + role.name())).collect(Collectors.toSet())
        );

        clubAuthMember.setName(clubAuthMember.getName());
        clubAuthMember.setFromSocial(clubAuthMember.isFromSocial());
        log.info(clubMember);
        log.info(clubAuthMember);
        return clubAuthMember;
    }
}



