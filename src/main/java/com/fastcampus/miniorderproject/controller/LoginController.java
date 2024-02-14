package com.fastcampus.miniorderproject.controller;


import com.fastcampus.miniorderproject.model.CartInfoModel;
import com.fastcampus.miniorderproject.model.ProductModel;
import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.service.CartService;
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
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param
     * @return
     */
    @PostMapping
    @ResponseBody
    public boolean login(@RequestBody UserModel userModel) {
        log.info("[START] LoginController login");
        boolean result = userService.validateIDAndPW(userModel);
        log.info("[END] LoginController login");
        return result;

    }

}
