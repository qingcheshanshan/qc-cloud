package cn.qc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName EurekaServer7002_App.java
 * @Description TODO
 * @createTime 2019年08月02日 10:10:00
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer7002_App {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer7002_App.class, args);
    }
}
