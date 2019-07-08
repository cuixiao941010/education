package com.cx.edu.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("web")
@Slf4j
public class webController {

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("demo")
    public String demo() {
        return "demo";
    }

    @RequestMapping("login2")
    public String login2() {
        return "login2";
    }
}
