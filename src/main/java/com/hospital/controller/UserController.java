package com.hospital.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private final Log log = LogFactory.getLog(getClass());

//    获得指定页码的用户信息
    @RequestMapping(value = "admin/user/{page}", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getUsers(@PathVariable String page){
        int _page = Integer.parseInt(page);
        List userList = userService.getAllUsers();
        int pageCount = ((userList.size() - 1) / 10) + 1;
        int fromIndex = (_page - 1) * 10;
        List<User> users = null;
        if (userList.size() > fromIndex){
            int toIndex = _page * 10;
            if (userList.size() > toIndex) {
                users = userList.subList(fromIndex, toIndex);
            } else {
                users = userList.subList(fromIndex, userList.size());
            }
        }
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            json = objectMapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            log.error(e);
            e.printStackTrace();
        }
        return "{\"data\":"+json+",\"pages\":"+pageCount+"}";
    }

//    更新用户
    @RequestMapping(value = "admin/user", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

//    增加用户
    @RequestMapping(value = "admin/user", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

//    删除用户
    @RequestMapping(value = "admin/user", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String deleteUser(@RequestBody User user) {
        int id = user.getId();
        userService.deleteUser(id);
        return "{\"result\":true}";
    }

//    认证用户登录信息
    @RequestMapping(value = "validate", method = RequestMethod.POST)
    @ResponseBody
    @JsonIgnoreProperties(ignoreUnknown=true)
    public Map<String, String> validate(@RequestBody Map map, HttpSession session) {
        BufferedReader in= null;
        String captcha =  "";
        try {
            in = new BufferedReader(new FileReader("code.txt"));
            captcha = in.readLine();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> result = new HashMap<String, String>();
        if (map.get("captcha").toString().equalsIgnoreCase(captcha) || captcha.equals("")) {  //忽略验证码大小写
            User u = userService.getUser(map.get("userName").toString());
            if (u != null && u.getUserPwd().equals(map.get("userPwd").toString())) {
                result.put("isValidated", "true");
                result.put("userType", u.getUserType().toString());
            } else {
                result.put("isValidated", "false");
                result.put("err", "填写信息错误");
            }
        } else {
            result.put("isValidated", "false");
            result.put("err", "填写信息错误");
        }
        return result;
    }
}
