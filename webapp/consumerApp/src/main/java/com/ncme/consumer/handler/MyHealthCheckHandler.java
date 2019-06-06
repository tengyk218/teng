package com.ncme.consumer.handler;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;

public class MyHealthCheckHandler implements HealthCheckHandler {

    @Autowired
    private HealthIndicator myHealthIndicator;

    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status status = myHealthIndicator.health().getStatus();
        if (status.equals(Status.UP)){
            return InstanceInfo.InstanceStatus.UP;
        } else {
            return InstanceInfo.InstanceStatus.DOWN;
        }
    }

}
