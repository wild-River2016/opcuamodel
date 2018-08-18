package com.mj.k4;

import com.mj.k4.opcua.config.OpcUaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2017/12/26
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
@Configuration
@EnableConfigurationProperties({OpcUaProperties.class})
public class K4Application extends SpringBootServletInitializer{
    public static void main(String[] args) throws UnknownHostException {
        Environment env = SpringApplication.run(K4Application.class, args).getEnvironment();
//        System.out.println("****************************");
//        System.out.println(env.getProperty("mybatis-plus.typeAliasesPackage"));
//        System.out.println("****************************");
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }
}
