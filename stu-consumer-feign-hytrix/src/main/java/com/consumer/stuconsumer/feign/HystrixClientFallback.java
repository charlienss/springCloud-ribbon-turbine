package com.consumer.stuconsumer.feign;

import com.consumer.stuconsumer.entity.Student;
import org.springframework.stereotype.Component;


@Component
public class HystrixClientFallback implements UserFeignClient {
  @Override
  public Student getAll(Integer id) {
    return null;
  }
}