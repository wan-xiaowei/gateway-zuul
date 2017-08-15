package com.me.FallBack;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @Author:Created by wanxiaowei
 * @Description:  微服务出错(找不到)回退
 * @Date:Created in 0:32 2017/8/16
 * @Modified By :
 */
@Component
public class CiaFallBack implements ZuulFallbackProvider{
    @Override
    public String getRoute() {
        return "cia-j";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                //fallback状态码
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                //数字类型状态码
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                //状态文本
                return getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                //响应体
                return new ByteArrayInputStream("cia服务不可用，稍后再试".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                MediaType mt = new MediaType("application","json", Charset.forName("utf-8"));
                httpHeaders.setContentType(mt);
                return httpHeaders;
            }
        };
    }
}
