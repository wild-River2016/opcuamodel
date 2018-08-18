package com.mj.k4.service;

import com.mj.k4.opcua.OpcUaClientTemplate;
import com.mj.k4.opcua.ualistener.UaConnectionListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author zhangyi
 * date: 3/5/16
 */
@Service
public class OpcuaService {

    @Inject
    private OpcUaClientTemplate opcUaClientTemplate;

    @Inject
    private UaConnectionListener uaConnectionListener;

//    @PostConstruct
    public void opcuaClientConnect() {
        opcUaClientTemplate.addConnectionListener(uaConnectionListener);
        opcUaClientTemplate.connectAlwaysInBackend();


//        NodeId nodeId = new NodeId(2, "Airbag.Airbag01.point_2");
//        try {
//            opcUaClientTemplate.writeNodeValue(nodeId, 20);
//            Variant variant = opcUaClientTemplate.readNodeVariant(nodeId);
//            variant.getValue();
//        } catch (OpcUaClientException e) {
//            e.printStackTrace();
//        }
    }
}
