package com.mph.coreapi;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Start {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(Start.class);

    public static void main(String[] args) throws IOException {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:service-deploy.xml");
            context.start();
        } catch (Exception e) {
            log.error("can not start service server", e);
            return;
        }

        log.info("service server started successfully");

        synchronized (Start.class) {
            while (true) {
                try {
                    Start.class.wait();
                } catch (InterruptedException e) {
                    log.info("server thread was interupted", e);
                }
            }
        }
    }
}
