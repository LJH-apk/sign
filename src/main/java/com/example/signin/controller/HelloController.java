package com.example.signin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;


@Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    //生成uid
    private String generateUid(){
        return UUID.randomUUID().toString();
    }

    @RequestMapping("/hello")
    public String Hello(){
        logger.info("打卡访问");
        return "sign.html";
    }

    @GetMapping("/location")
    public String showCheck(){
        return "location.html";
    }

    @GetMapping("/generate")
    public String showGenerate(){
        return "classroom.html";
    }

}
