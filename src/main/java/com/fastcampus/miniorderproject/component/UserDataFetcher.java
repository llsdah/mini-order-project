package com.fastcampus.miniorderproject.component;

import com.fastcampus.miniorderproject.model.UserModel;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserDataFetcher implements DataFetcher<UserModel> {

    @Override
    public UserModel get(DataFetchingEnvironment environment) {
        log.info("UserDataFetcher get");
        long id = environment.getArgument("id");
        // 여기서는 간단히 하드코딩된 데이터를 반환합니다.
        // 실제로는 데이터베이스에서 사용자 정보를 조회할 것입니다.
        return null;
    }

}