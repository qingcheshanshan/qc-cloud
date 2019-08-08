package cn.qc.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName GatewayConfig.java
 * @Description TODO
 * @createTime 2019年08月08日 13:49:00
 */
//@Configuration
public class GatewayConfig {

//   // @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/java/*")
//                        .filters(f -> f.stripPrefix(1))
//                        .uri("http://microservicecloud-dept")).build();
//    }
}
