package cn.wlih.test.controller;

import com.ruoyi.common.annotation.Anonymous;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/myTest")
public class TestController {

    @Anonymous
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

}
