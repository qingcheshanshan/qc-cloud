package cn.qc.controller;

import cn.qc.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName DeptController.java
 * @Description TODO
 * @createTime 2019年08月01日 15:01:00
 */
@RestController
@RequestMapping("/consumer")
public class DeptController {
  //  private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://microservicecloud-dept";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public List<Dept> listDept() {
        List<Dept> list = restTemplate.getForObject(REST_URL_PREFIX + "/dept", List.class);
        return list;
    }

    @GetMapping("/{id}")
    public Dept getDept(@PathVariable String id) {
        Dept dept = restTemplate.getForObject(REST_URL_PREFIX + "/dept/" + id, Dept.class);
        return dept;
    }

}
