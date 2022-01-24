package kr.co.js.club.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/smaple/")
public class SampleController {
    @GetMapping("/all")
    public void exAll(){
        log.info("모든 유저 접근 가능 ");
    }
    @GetMapping("/member")
    public void exMember(){
        log.info("회원만 접근 가능 ");
    }

    @GetMapping("/admin")
    public void exAdmin(){
        log.info("관리자만 접근 가능 ");
    }
}

