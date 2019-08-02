package cn.qc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName cn.qc.DeptProvider8001_App.java
 * @Description TODO
 * @createTime 2019年08月01日 09:56:00
 */
@SpringBootApplication
//@EnableEurekaClient
public class DeptProvider8001_App {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001_App.class, args);
    }

}
