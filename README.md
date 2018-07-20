# feignClient中配置ribbon和hytrix

#### 1.使用@FeignClient注解发现服务

服务提供者的controller:

```java
@RestController
public class StudentController {
    @Autowired
    private StudentService  studentService;

    @GetMapping("/getAll/{id}")
    public Student getAll(@PathVariable("id")Integer id){
        System.out.println("stu-provide:localhost:5865==>消费者查询学生时间:"+new Date().toLocaleString());
    Student stu =  studentService.getAllStu(id);
    return stu;

    }
}
```

消费者端:

```java
//使用FeignClient 告知发布方的应用名称 默认使用ribbon进行负载均衡
@FeignClient(name="stu-provide")
public interface TestFeign {
    @RequestMapping(value = "/getAll/{id}",method = RequestMethod.GET)
    public Student getAll(@PathVariable("id") Integer id);
}
```

在使用@FeignClient注解的时候 是默认使用了ribbon进行客户端的负载均衡的,默认的是随机的策略,那么如果我们想要更改策略的话,需要修改消费者yml中的配置,如下:

```properties
server:
  port: 9301
eureka:
  client:
    healthcheck:
      enable: true
    serviceUrl:
      defaultZone: http://user:password123@localhost:8761/eureka
#      defaultZone: http://eureka1:8761/eureka,http://eureka2:8762/eureka,http://eureka3:8763/eureka
  instance:
    hostname: localhost
    ipAddress: localhost
    prefer-ip-address: true
    instance-id: ${spring.application.name}:${spring.cloud.client.ipAddress}:${spring.application.instance_id:${server.port}}
spring:
  application:
    name: stu-consumer
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF-8;useSSL=true
    username: ****
    password: ****
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 30
    maxWait: 10000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMills: 300000
    validationQuery: SELECT 1 FROM DUAL
  session:
    store-type: none
#    配置ribbon
stu-provide:
  ribbon:

#    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule #配置规则 随机
#    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RoundRobinRule #配置规则 轮询
#    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RetryRule #配置规则 重试
#    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.WeightedResponseTimeRule #配置规则 响应时间权重
    NFLoadBalancerRuleClassName: com.netflix.loadbalancer.BestAvailableRule #配置规则 最空闲连接策略
    ConnectTimeout: 500 #请求连接超时时间
    ReadTimeout: 1000 #请求处理的超时时间
    OkToRetryOnAllOperations: true #对所有请求都进行重试
    MaxAutoRetriesNextServer: 2 #切换实例的重试次数
    MaxAutoRetries: 1 #对当前实例的重试次数
```

这里我们可以看到ribbon的策略主要有以下几种:

- com.netflix.loadbalancer.RandomRule #配置规则 随机
- com.netflix.loadbalancer.RoundRobinRule #配置规则 轮询
- com.netflix.loadbalancer.RetryRule #配置规则 重试
- com.netflix.loadbalancer.WeightedResponseTimeRule #配置规则 响应时间权重
- com.netflix.loadbalancer.BestAvailableRule #配置规则 最空闲连接策略

随机:几个提供者间随机访问

轮询:轮流访问

重试:在一段时间内通过RoundRobinRule选择服务实例，一段时间内没有选择出服务则线程终止 

响应时间权重:根据平均响应时间来计算权重

举个简单的列子，就是4个实例，A，B，C，D平均响应时间为10，40，80，100，所以总响应时间是10+40+80+100=230，每个实例权重为总响应时间与实际自身的平均响应时间的差的累积所得，所以A,B,C,D的权重分别如下：
 实例A: 230-10=220
 实例B：220+（230-40）=410
 实例C：410+（230-80）=560
 实例D：560+（230-100）=690

所以实例A：[0.220]
 实例B:(220,410]
 实例C:(410,560]
 实例D:(560，690)

 最空闲连接策略:当前空闲的提供者将优先被选取给消费者使用

下面以轮询策略演示为例,配置后的使用结果如下:

访问10次:http://localhost:9301/getAll/2:

![image.png](https://upload-images.jianshu.io/upload_images/12057079-1af0a4eb252e642f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

 

 

 

 