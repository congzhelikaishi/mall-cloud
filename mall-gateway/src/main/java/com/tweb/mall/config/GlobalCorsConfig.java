package com.tweb.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * 全局跨域配置
 * 注意：前端从网关进行调用时需要配置
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 请求方法
        config.addAllowedMethod("*");

        // 允许跨域访问的域名
        config.addAllowedOrigin("*");

        // 请求头
        config.addAllowedHeader("*");

        // 是否支持安全证书
        // 加上了这一句，大致意思是可以携带 cookie
        // 最终的结果是可以 在跨域请求的时候获取同一个 session
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());

        // 配置可以访问的地址
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
/*
  springcloud gateway跨域设置
  根源在Webflux上边，由于gateway使用的是webflux，而不是springmvc，所以需要先关闭webflux的cors，再从gateway的filter里边设置cors就行了

  配置yaml:
  spring:
    cloud:
      gateway:
        discovery:
        # 跨域
        globalcors:
          corsConfigurations:
            '[/**]':
              allowedHeaders: "*"
              allowedOrigins: "*"
              allowedMethods:
              - GET
                POST
                DELETE
                PUT
                OPTION
 */