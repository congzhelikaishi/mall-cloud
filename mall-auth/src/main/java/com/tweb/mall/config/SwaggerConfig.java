package com.tweb.mall.config;

import com.tweb.mall.config.BaseSwaggerConfig;
import com.tweb.mall.domain.SwaggerProperties;

public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.tweb.mall.controller")
                .title("mall认证中心")
                .description("mall认证中心相关接口文档")
                .contactName("admin")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
