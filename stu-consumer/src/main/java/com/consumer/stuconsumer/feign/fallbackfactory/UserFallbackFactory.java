package com.consumer.stuconsumer.feign.fallbackfactory;


import com.consumer.stuconsumer.entity.Student;
import com.consumer.stuconsumer.feign.ConsumerFeign;
import com.consumer.stuconsumer.feign.feignclientwithfactory.UserFeignWithFactory;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

//断路器设置
//这个是hystrix的类
@Component
public class UserFallbackFactory implements FallbackFactory<ConsumerFeign> {
    @Override
    public ConsumerFeign create(Throwable throwable) {
        return new UserFeignWithFactory(){
            @Override
            public Student getAll(Integer id) {
                return null;
            }
        };
    }
}
