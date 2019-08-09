package cn.qc.controller;

import cn.qc.entity.Dept;
import cn.qc.service.IDeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName DeptController.java
 * @Description TODO
 * @createTime 2019年08月01日 09:49:00
 */
@RestController
@RequestMapping("/dept")
public class DeptController {


    @Autowired
    private IDeptService deptService;

    @GetMapping
    public List<Dept> listDept() {
        List<Dept> list = deptService.list();
        return list;
    }

    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "fallBack")
    public Dept getDept(@PathVariable String id) {
        Dept dept = deptService.getById(id);
        if (dept == null) {
            throw new RuntimeException("没有对应数据");
        }
        return dept;
    }

    public Dept fallBack(@PathVariable String id) {
        Dept dept = new Dept();
        dept.setName("该id没有对应的信息" + id).setDbSource("no ");
        System.err.println("测试2019年8月8日16:11:02-=----------hystrix");
        return dept;
    }

}
