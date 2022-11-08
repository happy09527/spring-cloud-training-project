学习springcloud微服务架构所做练习，其中不涉及具体的业务流程，仅仅是为了初学时各种组件与cloud框架的配合使用。
## 学习流程与模块间的关系


#### hystrix断路器,进行服务降级，熔断、监控等功能
模块
cloud-consumer-feign-hystrix-order80 模拟客户端 访问接口localhost/consumer/payment/hystrix/ok/1
cloud-provider-hystrix-payment8001 模拟服务端 访问接口localhost:8001/payment/hystrix/ok/1
cloud-single-eureka-serve7003 模拟注册中心 接口localhost:7003

使用jmeter进行压力测试
其中，监控功能模块为 cloud-consumer-hystrix-dashboard9001，接口为http://localhost:9001/hystrix
监控url 8001/hystrix.stream


### gateway 网关
模块
cloud-gateway9527
payment8001
cloud-single-eureka-serve7003 
访问url http://localhost:9527/payment/get/1