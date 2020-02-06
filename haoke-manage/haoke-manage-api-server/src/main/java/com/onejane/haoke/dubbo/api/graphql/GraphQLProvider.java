package com.onejane.haoke.dubbo.api.graphql;

import com.onejane.haoke.dubbo.api.service.HouseResourcesService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;

// 实现的功能：将GraphQL对象载入到Spring容器，并且完成GraphQL对象的初始化的功能
@Component
public class GraphQLProvider {
    @Bean
    public GraphQL graphQL() {
        return this.graphQL;
    }
    private GraphQL graphQL;

    @Autowired
    private HouseResourcesService houseResourcesService;

    //实现对GraphQL对象的初始化
    @PostConstruct
    public void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:haoke.graphqls");
        this.graphQL = GraphQL.newGraphQL(buildGraphQLSchema(file)).build();


    }

    private GraphQLSchema buildGraphQLSchema(File file) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(file);
        return new SchemaGenerator().makeExecutableSchema(typeRegistry, buildWiring());
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("HaokeQuery", builder -> builder.dataFetcher("HouseResources",  environment -> {
                    Long id = environment.getArgument("id");
                    return this.houseResourcesService.queryHouseResourcesById(id);


                })).build();
    }

}