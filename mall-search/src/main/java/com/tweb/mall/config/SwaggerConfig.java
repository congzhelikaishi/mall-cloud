package com.tweb.mall.config;

import com.tweb.mall.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.tweb.mall.controller")
                .title("mall搜索系统")
                .description("mall搜索相关接口文档")
                .contactName("mall")
                .version("1.0")
                .enableSecurity(false)
                .build();
    }
}
