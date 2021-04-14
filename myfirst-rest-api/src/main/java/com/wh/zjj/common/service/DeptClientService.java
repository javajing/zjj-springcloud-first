package com.wh.zjj.common.service;

import com.wh.zjj.common.dao.mapper.Dept;
import com.wh.zjj.common.factory.DeptClientServiceFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * @author zjj
 * 修改microservicecloud-api工程，根据已经有的DeptClientService接口
 * 一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 */
// value --->指定调用哪个服务
// fallbackFactory--->熔断器的降级提示
@FeignClient(value = "SPRINGCLOUD-FIRST-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

    /**
     * 采用Feign我们就可以使用SpringMVC的注解来对提供者的服务进行绑定！
     */
    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(Dept dept);
}
