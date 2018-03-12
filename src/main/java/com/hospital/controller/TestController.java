package com.hospital.controller;

import com.hospital.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired(required = true)
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
//        实际返回的是views/test.jsp ,spring-mvc.xml中配置过前后缀
        return "test";
    }

    @RequestMapping(value = "/springtest", method = RequestMethod.GET)
    @ResponseBody
    public String springTest() {
        return testService.test();
    }
}
