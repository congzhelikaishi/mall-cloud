package com.tweb.mall.config;

import com.tweb.mall.domain.SwaggerProperties;

public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.tweb.mall.controller")
                .title("mall后台系统")
                .description("mall后台相关接口文档")
                .contactName("admin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
