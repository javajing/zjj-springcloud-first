package com.wh.zjj.feign.controller;

import com.wh.zjj.api.dto.param.DeptParam;
import com.wh.zjj.api.dto.result.DeptDto;
import com.wh.zjj.api.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignController {

    @Autowired
    private DeptClientService service;

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public DeptDto get(@PathVariable("id") Long id) {
        return this.service.get(id);
    }

    @RequestMapping(value = "/consumer/dept/all")
    public List<DeptDto> list() {
        return this.service.list();
    }

    @RequestMapping(value = "/consumer/dept/add")
    public Object add(DeptParam dept) {
        return this.service.add(dept);
    }

}
