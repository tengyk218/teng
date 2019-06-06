package com.ncme.consumer.handler;

import com.netflix.discovery.DiscoveryManager;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class MyHealthIndicator implements HealthIndicator, ServletContextListener {

    public boolean health = true;

    public Health health() {
        if (health) {
            return new Health.Builder(Status.UP).build();
        } else {
            return new Health.Builder(Status.DOWN).build();
        }
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DiscoveryManager.getInstance().shutdownComponent();
    }

    public boolean isHealth() {
        return health;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }
}
