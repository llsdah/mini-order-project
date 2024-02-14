package com.fastcampus.miniorderproject.service;

import com.fastcampus.miniorderproject.config.MyJwtProvider;
import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.repository.CartRepository;
import com.fastcampus.miniorderproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MyJwtProvider myJwtProvider;

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

    public UserModel find(long id) {
        Optional<UserModel> opResult = userRepository.findById(id);
        return opResult.orElse(null);
    }

    public boolean validateIDAndPW(UserModel userModel) {

        Optional<UserModel> opResult = userRepository.findByEmailAndPassword(
                userModel.getEmail(),userModel.getPassword());

        String token = "";

        if(opResult.isPresent()){
            token = myJwtProvider.generateToken(opResult.get());
            log.info("token : "+token);
            return true;
        }

        log.info("[FAIL] validateIDAndPW");
        return false;

    }
}
