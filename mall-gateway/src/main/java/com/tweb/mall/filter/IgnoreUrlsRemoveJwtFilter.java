package com.tweb.mall.filter;

import com.tweb.mall.constant.AuthConstant;
import com.tweb.mall.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * 白名单路径访问时需要移除JWT请求头
 */
@Component
public class IgnoreUrlsRemoveJwtFilter implements WebFilter {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();  // 获取请求uri地址
        PathMatcher pathMatcher = new AntPathMatcher();

        //白名单路径移除JWT请求头
        List<String> ignoreUrls = ignoreUrlsConfig.getUrls();
        for (String ignoreUrl : ignoreUrls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                request = exchange.getRequest().mutate().header(AuthConstant.JWT_TOKEN_HEADER, "").build();  // mutate()返回生成器以更改此请求的属性，方法是将其包装为 ServerHttpRequestDecorator 并返回已更改的值或委派回此实例
                exchange = exchange.mutate().request(request).build();  // request()覆盖ServerHttpRequest
                return chain.filter(exchange);  // filter()委托给链中的下一个WebFilter
            }
        }
        return chain.filter(exchange);
    }
}
