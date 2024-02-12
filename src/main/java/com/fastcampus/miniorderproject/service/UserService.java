package com.fastcampus.miniorderproject.service;

import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.repository.CartRepository;
import com.fastcampus.miniorderproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;


    /**
     * 회원 생성 동시에 장바구니 생성
     * @param userModel
     * @return
     */
    public UserModel saveUser(UserModel userModel) {
        UserModel result = userRepository.save(userModel);
        CartModel cartModel = new CartModel();
        cartModel.setUser(result);
        cartRepository.save(cartModel);
        return result;
    }
}
