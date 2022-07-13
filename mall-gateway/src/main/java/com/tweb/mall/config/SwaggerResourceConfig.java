package com.tweb.mall.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger资源配置
 * 聚合各个服务的swagger接口
 */
@Slf4j
@Component
@Primary
@AllArgsConstructor  // 注解在类上, 为类提供无参,有指定必须参数, 全参构造函数
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    private final RouteLocator routeLocator;
    private final GatewayProperties gatewayProperties;

    /**
     * 这个类是核心，这个类封装的是SwaggerResource，即在swagger-ui.html页面中顶部的选择框，选择服务的swagger页面内容。
     * RouteLocator：获取spring cloud gateway中注册的路由
     * RouteDefinitionLocator：获取spring cloud gateway路由的详细信息
     * RestTemplate：获取各个配置有swagger的服务的swagger-resources
     */
    @Override
    public List<SwaggerResource> get() {

        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        //获取所有路由的ID，取出gateway的route
        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));

        //过滤出配置文件中定义的路由->过滤出Path Route Predicate->根据路径拼接成api-docs路径->生成SwaggerResource
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(route -> {  // 1.arraylist.contains(Object obj)如果指定的元素存在于动态数组中，则返回 true 如果指定的元素不存在于动态数组中，则返回 false  2.routeDefinition.getId()id：路由id，默认为uuid predicates：PredicateDefinition 路由断言定义列表 filters：FilterDefinition 过滤器定义列表 uri：URI 转发地址  order：优先级
            route.getPredicates().stream()  // route.getPredicates():获取路由断言定义列表
                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))  // 1.equalsIgnoreCase() 方法用于将字符串与指定的对象比较，不考虑大小写  2.predicateDefinition.getName():获取断言内容
                    .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                    .replace("**", "v2/api-docs"))));  // replace(char searchChar, char newChar) 方法通过用 newChar 字符替换字符串中出现的所有 searchChar 字符，并返回替换后的新字符串
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}", name, location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
