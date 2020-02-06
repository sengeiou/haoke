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
import java.util.List;

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
    @Autowired
    private List<MyDataFetcher> myDataFetchers; //注入容器中所有的MyDataFetcher实现类

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

    /**
     * 每增加一个查询，将在buildWiring增加方法，难以维护。
     * 1. 编写MyDataFetcher接口
     * 2. 所有实现查询的逻辑HouseResourcesDataFetcher都实现该接口
     * 3. 在GraphQLProvider中使用该接口的实现类进行处理
     * 4. 以后需要新增查询逻辑只需要增加实现类即可
     * @return
     */
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("HaokeQuery", builder -> {
                            for (MyDataFetcher myDataFetcher : myDataFetchers) {
                                builder.dataFetcher(myDataFetcher.fieldName(),
                                        environment -> myDataFetcher.dataFetcher(environment));
                            }
                            return builder;
                        }
                )
                .build();
    }

}