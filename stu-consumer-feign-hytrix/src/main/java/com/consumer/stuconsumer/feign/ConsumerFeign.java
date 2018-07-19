//package com.consumer.stuconsumer.feign;
//
//import com.consumer.stuconsumer.entity.Student;
//import com.consumer.stuconsumer.feign.fallbackfactory.UserFallbackFactory;
//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
////使用FeignClient 告知发布方的应用名称 默认使用ribbon进行负载均衡
//@FeignClient(name = "stu-provide",fallbackFactory = UserFallbackFactory.class)
//public interface ConsumerFeign {
////    @GetMapping("/getAll/{id}")
////    public Student getAll(Integer id);
//
//    @RequestMapping(value = "/getAll/{id}",method = RequestMethod.GET)
//    public Student getAll(@PathVariable("id") Integer id);
//
//}
