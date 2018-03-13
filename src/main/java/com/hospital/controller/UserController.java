package com.hospital.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.hospital.entity.User;
import com.hospital.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by WangCheng on 2018/3/13.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    private final Log log = LogFactory.getLog(getClass());

    @RequestMapping(value = "getUser/{page}", method = RequestMethod.GET, produces="application/json")
    @ResponseBody
    public String getUsers(@PathVariable String page){
        int _page = Integer.parseInt(page);
        List userList = userService.getAllUsers();
        int pageCount = (userList.size() / 10) + 1;
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

    @RequestMapping(value = "updateUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

    @RequestMapping(value = "saveUser", method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public String saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            return "{\"result\":false}";
        }
        return "{\"result\":true}";
    }

    @RequestMapping(value = "deleteUser/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteUser(@PathVariable String id) {
        int _id = Integer.parseInt(id);
        userService.deleteUser(_id);
        return "{\"result\":true}";
    }
}
