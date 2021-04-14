package com.wh.zjj.api.factory;

import com.wh.zjj.api.dto.param.DeptParam;
import com.wh.zjj.api.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Feign中使用断路器
 * 这里主要是处理异常出错的情况(降级/熔断时服务不可用，fallback就会找到这里来)
 * @author zjj
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public DeptParam get(long id) {
                return new DeptParam().setDeptno(id).setDname("该ID：" + id + "没有找到,降级处理")
                        .setDbSource("error");
            }

            @Override
            public List<DeptParam> list() {
                return null;
            }

            @Override
            public boolean add(DeptParam dept) {
                return false;
            }
        };
    }
}
