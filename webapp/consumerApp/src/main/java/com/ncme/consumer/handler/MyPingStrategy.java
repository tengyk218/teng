package com.ncme.consumer.handler;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerPing;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

public class MyPingStrategy  extends AbstractLoadBalancerPing {
    BaseLoadBalancer lb = null;

    public MyPingStrategy() {
    }

    public BaseLoadBalancer getLb() {
        return this.lb;
    }

    public void setLb(BaseLoadBalancer lb) {
        this.lb = lb;
    }

    public boolean isAlive(Server server) {
        boolean isAlive = true;
        if (server != null && server instanceof DiscoveryEnabledServer) {
            DiscoveryEnabledServer dServer = (DiscoveryEnabledServer)server;
            InstanceInfo instanceInfo = dServer.getInstanceInfo();
            if (instanceInfo != null) {
                InstanceStatus status = instanceInfo.getStatus();
                if (status != null) {
                    isAlive = status.equals(InstanceStatus.UP);
                }
            }
        }

        return isAlive;
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }

}
