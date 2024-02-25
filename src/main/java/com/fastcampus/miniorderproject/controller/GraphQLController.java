package com.fastcampus.miniorderproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.GraphQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import graphql.ExecutionResult;

@Slf4j
@Controller
public class GraphQLController {

    @Autowired
    private GraphQL graphQL;

    @PostMapping(value = "/graphql", consumes = "application/json", produces = "application/json")
    public ExecutionResult graphql(@RequestBody String query) {
        log.info("GraphQL Start");
        log.info("GraphQL : "+query);
        ObjectMapper objectMapper = new ObjectMapper();
        //String test = objectMapper.
        log.debug("이거다 이거 ");
        ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).build();
        return graphQL.execute(executionInput);
    }
}
