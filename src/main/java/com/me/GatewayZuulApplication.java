package com.me;

import com.me.Filter.PreRequestLogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy  //代理使用ribbon定位Eureka的微服务,整合了Hytrix,可监控
public class GatewayZuulApplication {
   @Bean
   public PreRequestLogFilter preRequestLogFilter(){
   	return new PreRequestLogFilter();
   }
	public static void main(String[] args) {
		SpringApplication.run(GatewayZuulApplication.class, args);
	}
}
