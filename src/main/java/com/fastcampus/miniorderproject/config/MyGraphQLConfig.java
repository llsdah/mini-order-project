package com.fastcampus.miniorderproject.config;

import com.fastcampus.miniorderproject.component.UserDataFetcher;
import com.fastcampus.miniorderproject.component.UserResolver;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.File;

@Slf4j
@Configuration
public class MyGraphQLConfig {

    @Autowired
    private UserDataFetcher userDataFetcher;

    @Autowired
    private UserResolver userResolver;

    @Bean
    public GraphQL graphQL() {
        log.info("graphQL");
        File schemaFile = new File("src/main/resources/schema.graphql"); // 스키마 파일 경로 지정
        log.info("graphQL file");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
        log.info("typeRegistry");
        RuntimeWiring wiring = buildWiring(); // 스키마와 리졸버 연결
        log.info("RuntimeWiring");
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
        log.info("GraphQLSchema");
        return GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        log.info("buildWiring");
        //Resolver 등록
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", builder -> builder
                        //.dataFetcher("getUser", userDataFetcher)
                        .dataFetcher("getUser", env -> userResolver.getUser(env.getArgument("id")))
                        .dataFetcher("getAllUsers", env -> userResolver.getAllUsers()))
                .type("Mutation", builder -> builder
                        .dataFetcher("createUser", env -> userResolver.createUser(
                                env.getArgument("name"),
                                env.getArgument("password"),
                                env.getArgument("email"),
                                env.getArgument("address"),
                                env.getArgument("nickName"),
                                env.getArgument("authority")
                        ))
                        .dataFetcher("updateUser", env -> userResolver.updateUser(env))
                        .dataFetcher("deleteUser", env -> userResolver.deleteUser(env.getArgument("id"))))
                .type("Subscription", builder -> builder
                        .dataFetcher("userCreated", env -> userResolver.userCreated()))
                .build();
    }

}