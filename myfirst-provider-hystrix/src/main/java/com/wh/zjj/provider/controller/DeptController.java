package com.wh.zjj.provider.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
 * 注意: hystrix的默认接口调用超时为1秒,超过1秒不响应就会降级熔断
 */
@RestController
@Slf4j
public class DeptController {

    @Autowired
    private IDeptService deptService;
    @Qualifier("discoveryClient")
    @Autowired
    private DiscoveryClient client;

    private static final String DB = "1";

    @PostMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept) {
        dept.setDbSource(DB);
        return deptService.save(dept);
    }

    @GetMapping(value = "/dept/get/{id}")
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "hystrixCallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
    })
    public Dept get(@PathVariable("id") Long id) {
        log.info("i am 8001, id:{}", id);
        Dept dept = deptService.getById(id);
        if(dept == null){
            throw new RuntimeException("该ID:" + id + "没有数据");
        }
        return dept;
    }

    /**
     * @author zhangjingjing
     * @date 2021/4/14 16:39
     * @param id
     * @return com.wh.zjj.common.base.entity.Dept
     * @desc (降级回调接口)
     */
    public Dept hystrixCallBack(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).setDname("hystrix: 该id:" + id + "没有db数据")
                .setDbSource("no data");
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

        List<ServiceInstance> srvList = client.getInstances("MYFIRST-PROVIDER-DEPT");
        for (ServiceInstance element : srvList) {
            log.info(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t"
                    + element.getUri());
        }
        return this.client;
    }

}
