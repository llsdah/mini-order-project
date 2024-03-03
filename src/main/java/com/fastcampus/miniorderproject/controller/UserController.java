package com.fastcampus.miniorderproject.controller;

import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param
     * @return
     */
    @PostMapping
    @ResponseBody
    public UserModel createUser(@RequestBody UserModel userModel) {
        log.info("[START] UserController createUser");
        log.info("userModel : "+userModel.toString());
        UserModel result = userService.saveUser(userModel);
        log.info("result : "+result.toString());
        log.info("[END] UserController createUser ");
        return result;
    }

}
