package com.tweb.mall.component;

import cn.hutool.json.JSONUtil;
import com.tweb.mall.api.CommonResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * 自定义返回结果：没有登录或token过期时
 */
@Component
public class RestAuthenticationEntryPoint implements ServerAuthenticationEntryPoint {
    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK); // 状态码200
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);  //根据自己的需要动态添加你想要的content type   MediaType.APPLICATION_JSON_VALUE:表示生成json格式的数据
        response.getHeaders().set("Access-Control-Allow-Origin","*");  // *表示所有的域都可以接收
        response.getHeaders().set("Cache-Control","no-cache");  // 设置Content-Type响应头  no-cache: 指定不缓存响应，表明资源不进行缓存。但是设置了no-cache之后并不代表浏览器不缓存，而是在缓存前要向服务器确认资源是否被更改。因此有的时候只设置no-cache防止缓存还是不够保险，还可以加上private指令，将过期时间设为过去的时间
        String body= JSONUtil.toJsonStr(CommonResult.unauthorized(e.getMessage()));  // 未登录返回结果转换为json数据
        //  设置body
        DataBuffer buffer =  response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));  // 1.bufferFactory()：创建缓冲区  2.wrap(byte[] bytes)将给定数组包装在byteDataBuffer  3.body.getBytes(Charset.forName("UTF-8")、getBytes(String charsetName): 使用指定的字符集将字符串编码为 byte 序列，并将结果存储到一个新的 byte 数组中
        return response.writeWith(Mono.just(buffer));  //  1.writeWith(Publisher<? extends DataBuffer> body)使用给定的发布服务器将消息正文写入基础 HTTP 层。  2.Mono.just()创建一个Flux，它发出所提供的元素，然后完成，just()：可以指定序列中包含的全部元素。创建出来的 Mono序列在发布这些元素之后会自动结束
    }
}
