package com.onejane.haoke.dubbo.api.controller;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("graphql")
@Controller
@CrossOrigin
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;
    /**
     * 实现GraphQL查询
     *
     * @param query
     * @return
     */
    @GetMapping
    @ResponseBody
    public Map<String, Object> query(@RequestParam("query") String query) {
        return this.graphQL.execute(query).toSpecification();
    }
}