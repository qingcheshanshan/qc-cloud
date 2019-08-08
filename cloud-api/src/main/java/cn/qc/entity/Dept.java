package cn.qc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dinghao
 * @version 1.0.0
 * @ClassName Dept.java
 * @Description TODO
 * @createTime 2019年08月01日 09:27:00
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private String id;
    private String name;
    private String dbSource;
}
