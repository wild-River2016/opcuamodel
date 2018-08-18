package com.mj.k4.opcua.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Properties specific to OpcUa Client.
 * <p>
 * <p>
 * Properties are configured in the application.yml file.
 * </p>
 *
 * @author liumin
 */
@Data
@ConfigurationProperties(prefix ="opcua", ignoreUnknownFields = false)
public class OpcUaProperties {

    private Retry retry;     //opcuc 连接属性
    private long publishingRate;  //推送响应速率
    private List<Map<String, String>> plcList;  //对应的opc 站点队列
    private String address;
    private List<String> positiveStationOneSubscribeNodes;

    private List<String> positiveStationFiveSubscribeNodes;

    private List<String> positiveStationSixSubscribeNodes;

    private List<String> positiveStationSevenSubscribeNodes;

    private List<String> weldJointStationOneSubscribeNodes;

    private List<String> weldJointStationTwoSubscribeNodes;

    private List<String> oppositeStationOneSubscribeNodes;

    private List<String> oppositeStationTwoSubscribeNodes;

    private List<String> oppositeStationThreeSubscribeNodes;

    private List<String> oppositeStationFourSubscribeNodes;

    private List<String> oppositeStationFiveSubscribeNodes;

    private List<String> oppositeStationSixSubscribeNodes;

    private List<String> oppositeStationSevenSubscribeNodes;

    private List<String> oppositeStationEightSubscribeNodes;

    private List<String> oppositeStationNineSubscribeNodes;

    private List<String> oppositeStationTenSubscribeNodes;

    private List<String> oppositeStationElevenSubscribeNodes;

    private List<String> oppositeStationTwelveSubscribeNodes;

    private List<String> oppositeStationThirteenSubscribeNodes;


    public static class Retry {
        private long connBackOffPeriod = 10000L;
        private int maxAttempts = 3;
        private long backOffPeriod = 1000L;

        public long getConnBackOffPeriod() {
            return connBackOffPeriod;
        }

        public void setConnBackOffPeriod(long connBackOffPeriod) {
            this.connBackOffPeriod = connBackOffPeriod;
        }

        public int getMaxAttempts() {
            return maxAttempts;
        }

        public void setMaxAttempts(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }

        public long getBackOffPeriod() {
            return backOffPeriod;
        }

        public void setBackOffPeriod(long backOffPeriod) {
            this.backOffPeriod = backOffPeriod;
        }
    }
}
