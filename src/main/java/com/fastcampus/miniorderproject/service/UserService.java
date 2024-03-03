package com.fastcampus.miniorderproject.service;

import com.fastcampus.miniorderproject.model.CartModel;
import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.repository.CartRepository;
import com.fastcampus.miniorderproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    //@Autowired
    //private MyJwtProvider myJwtProvider;

    /**
     * 회원 생성 동시에 장바구니 생성
     * @param userModel
     * @return
     */
    public UserModel saveUser(UserModel userModel) {
        log.info("saveUser");
        UserModel result = userRepository.save(userModel);
        CartModel cartModel = new CartModel();
        cartModel.setUser(result);
        cartRepository.save(cartModel);
        log.info("saveUser result : "+ result);
        return result;
    }

    public UserModel find(long id) {
        Optional<UserModel> opResult = userRepository.findById(id);
        return opResult.orElse(null);
    }

    public boolean validateIDAndPW(UserModel userModel) {

        Optional<UserModel> opResult = userRepository.findByEmailAndPassword(
                userModel.getEmail(),userModel.getPassword());

        if(opResult.isPresent()){
            //String token = myJwtProvider.generateToken(opResult.get());
            //log.info("token : "+ token";
            return true;
        }

        log.info("[FAIL] validateIDAndPW");
        return false;

    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
