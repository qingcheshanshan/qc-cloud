package cn.qc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName Zuul_Gateway_9527_App.java
 * @Description TODO
 * @createTime 2019年08月08日 10:58:00
 */
@SpringBootApplication
public class Zuul_Gateway_9527_App {
    public static void main(String[] args) {
        //http://localhost:9527/microservicecloud-dept/dept
        SpringApplication.run(Zuul_Gateway_9527_App.class, args);
    }
}
