package cn.qc.controller;

import cn.qc.service.IDeptService;
import cn.qc.entity.Dept;
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
    public Dept getDept(@PathVariable String id) {
        Dept dept = deptService.getById(id);
        return dept;
    }


}
