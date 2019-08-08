package cn.qc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.qc.entity.Dept;
import cn.qc.mapper.DeptMapper;
import org.springframework.stereotype.Service;
import cn.qc.service.IDeptService;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName DeptServiceImpl.java
 * @Description TODO
 * @createTime 2019年08月01日 09:45:00
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
}
