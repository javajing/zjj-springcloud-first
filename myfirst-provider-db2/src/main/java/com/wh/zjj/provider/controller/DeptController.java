package com.wh.zjj.provider.controller;

import com.wh.zjj.common.base.entity.Dept;
import com.wh.zjj.common.base.service.IDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zjj
 */
@RestController
@Slf4j
public class DeptController {

    @Autowired
    private IDeptService deptService;
    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    private static final String DB = "2";

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        dept.setDbSource(DB);
        return deptService.save(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        log.info("i am 8001, id:{}", id);
        return deptService.getById(id);
    }

    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptService.list();
    }

    /**
     * 服务发现：可以得到当前在eureka中已注册的服务
     * @return
     */
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        log.info("**********" + list);

        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance element : srvList) {
            log.info(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

}
