package com.fastcampus.miniorderproject.component;


import com.fastcampus.miniorderproject.model.UserModel;
import com.fastcampus.miniorderproject.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class UserResolver
implements GraphQLQueryResolver, GraphQLMutationResolver, GraphQLSubscriptionResolver {

    @Autowired
    private UserService userService;

    public UserModel getUser(String strId) {
        log.info("UserResolver getUser");
        return userService.find(Long.parseLong(strId));
    }

    public List<UserModel> getAllUsers() {
        log.info("UserResolver getAllUsers");
        return userService.findAll();
    }


    public UserModel createUser(String name, String email,String password,String address, String nickName, String authority) {
        log.info("UserResolver createUser");

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setPassword(password);
        userModel.setEmail(email);
        userModel.setAddress(address);
        userModel.setNickName(nickName);
        userModel.setAuthority(authority);
        log.info("user Model {}",userModel.toString());
        UserModel newUser = userService.saveUser(userModel);
        log.info("user Model {}",newUser.toString());
        return newUser;
    }

    public UserModel updateUser(DataFetchingEnvironment environment) {
        log.info("UserResolver updateUser");
        Map<String,Object> map = environment.getArguments();
        for(String key : map.keySet()){
            log.info("key { } value { }",key,map.get(key));
        };
        return null;
    }

    public Boolean deleteUser(String strId) {

        return userService.deleteUser(Long.parseLong(strId));
    }

    public Publisher<UserModel> userCreated() {
        UserModel temp = new UserModel();
        temp.setName("test");
        temp.setEmail("test");
        return Flux.create(sink -> {
            sink.next(userService.saveUser (temp));
        });
    }


}
