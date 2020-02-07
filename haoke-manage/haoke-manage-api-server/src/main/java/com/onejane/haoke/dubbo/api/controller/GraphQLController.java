package com.onejane.haoke.dubbo.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("graphql")
@Controller
@CrossOrigin // 设置跨域
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;

    private static final ObjectMapper MAPPER = new ObjectMapper();
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

    // Apollo Client发起的数据请求为POST请求，现在实现的GraphQL仅仅实现了GET请求处理
    @PostMapping
    @ResponseBody
    public Map<String, Object> postQuery(@RequestBody String json) {

        try {
            JsonNode jsonNode = MAPPER.readTree(json);
            if(jsonNode.has("query")){
                String query = jsonNode.get("query").textValue();
                return this.graphQL.execute(query).toSpecification();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Object> error = new HashMap<>();
        error.put("status", 500);
        error.put("msg", "查询出错");
        return error;
    }
}