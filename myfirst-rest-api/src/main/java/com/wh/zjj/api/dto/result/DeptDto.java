package com.wh.zjj.api.dto.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zjj
 */
@Data
public class DeptDto implements Serializable// entity --orm--- db_table
{
    private Long deptno;
    private String dname;
    private String dbSource;

}
