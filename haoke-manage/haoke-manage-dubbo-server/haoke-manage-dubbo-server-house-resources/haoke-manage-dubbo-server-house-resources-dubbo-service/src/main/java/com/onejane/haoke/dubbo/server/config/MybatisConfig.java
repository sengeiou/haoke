package com.onejane.haoke.dubbo.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.onejane.haoke.dubbo.server.mapper")
@Configuration
public class MybatisConfig {

}