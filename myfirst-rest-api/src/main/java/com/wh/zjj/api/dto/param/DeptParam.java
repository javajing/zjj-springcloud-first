package com.wh.zjj.api.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;


/**
 * @author zjj
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class DeptParam implements Serializable// entity --orm--- db_table
{
    private Long deptno;
    private String dname;
    private String dbSource;

}
