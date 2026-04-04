package cn.wlih.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myTest")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
