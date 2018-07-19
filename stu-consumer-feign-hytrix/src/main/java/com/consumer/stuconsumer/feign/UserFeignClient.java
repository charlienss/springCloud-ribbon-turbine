package com.consumer.stuconsumer.feign;

import com.consumer.stuconsumer.entity.Student;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "stu-provide", fallback = HystrixClientFallback.class)
public interface UserFeignClient {
  @RequestMapping(value = "/getAll/{id}",method = RequestMethod.GET)
  public Student getAll(@PathVariable("id") Integer id);
}
