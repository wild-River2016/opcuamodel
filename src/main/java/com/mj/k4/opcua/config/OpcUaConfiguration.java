package com.mj.k4.opcua.config;

import com.mj.k4.opcua.*;
import com.mj.k4.opcua.util.OpcUaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author liumin
 */
@Configuration
public class OpcUaConfiguration  extends OpcUaUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpcUaConfiguration.class);
    /**
     * 通过Spring 容器来创建对应的OpcUa实例化工厂对象
     * @param opcUaProperties
     * @return
     */
    @Bean
    List<OpcUaClientFactory> opcUaClientFactory(OpcUaProperties opcUaProperties){
        List<OpcUaClientFactory>  opcUaClientFactoryList=new ArrayList<OpcUaClientFactory>();
        List<Map<String, String>>  plcList=opcUaProperties.getPlcList();
        for (Map<String, String> plc : plcList) {
            AutoReconnectUaClientFactory uaClientFactory = new AutoReconnectUaClientFactory();
            uaClientFactory.setUaAddress(plc.get("address"));  //设置实例化工厂的ip地址
            if( ! OPCUA_LIST.contains(plc.get("address"))){
                OPCUA_LIST.add(plc.get("address"));  //判断添加到工具类方法中
            }
            opcUaClientFactoryList.add(uaClientFactory);   //将工厂对象添加到集合中
        }
        return opcUaClientFactoryList;
    };
    /**
     * @param opcUaClientFactorylist
     * @param properties
     * @return
     * @throws OpcUaClientException
     *   根据OpcUa工厂对象,以及OpcUa属性对象来进行实例化OpcUa服务的模板方法
     *      在没有使用归还给线程池  (destroyMethod ="close")
     */
    @Bean
    public OpcUaClientTemplate OpcUaClientTempate(List<OpcUaClientFactory> opcUaClientFactorylist, OpcUaProperties properties) throws OpcUaClientException {
        LOGGER.info("OpcUaClientTemplate CREATE.");
        return   new OpcUaClientTemplate(opcUaClientFactorylist,properties);
    };


    /**
     * @param properties
     * @return
     *   通过Spring对象 创建对应的OpcUa 注册节点对象
     */
    @Bean
    public OpcUaSubscribeNodes opcUaSubscribeNodes (OpcUaProperties properties){
        LOGGER.info("opcUaSubscribeNodes CREATE.");
        return new OpcUaSubscribeNodes(properties) ;
    }
}
