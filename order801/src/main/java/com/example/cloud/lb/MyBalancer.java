package com.example.cloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface MyBalancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
