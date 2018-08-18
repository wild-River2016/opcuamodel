package com.mj.k4.opcua.ualistener;

import com.mj.k4.opcua.OpcUaClientConnectionListener;
import com.mj.k4.opcua.OpcUaClientException;
import com.mj.k4.opcua.OpcUaClientTemplate;
import com.mj.k4.opcua.OpcUaSubscribeNodes;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import com.prosysopc.ua.client.UaClient;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zy
 *         date: 3/5/16
 */
@Component
public class UaConnectionListener implements OpcUaClientConnectionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(UaConnectionListener.class);

    private static final Map<String, MonitoredDataItemListener> LISTENER_MAP = new ConcurrentHashMap<>();

    @Inject
    private OpcUaClientTemplate opcUaClientTemplate;

    @Inject
    private OpcUaSubscribeNodes opcUaSubscribeNodes;

    @Inject
    private PositiveStationOneListener positiveStationOneListener;

    @Inject
    private PositiveStationFiveListener positiveStationFiveListener;

    @Inject
    private PositiveStationSixListener positiveStationSixListener;

    @Inject
    private PositiveStationSevenListener positiveStationSevenListener;

    @Inject
    private WeldJointStationOneListener weldJointStationOneListener;

    @Inject
    private WeldJointStationTwoListener weldJointStationTwoListener;

    @Inject
    private OppositeStationEightListener oppositeStationEightListener;

    @Inject
    private OppositeStationElevenListener oppositeStationElevenListener;

    @Inject
    private OppositeStationFiveListener oppositeStationFiveListener;

    @Inject
    private OppositeStationFourListener oppositeStationFourListener;

    @Inject
    private OppositeStationNineListener oppositeStationNineListener;

    @Inject
    private OppositeStationOneListener oppositeStationOneListener;

    @Inject
    private OppositeStationSevenListener oppositeStationSevenListener;

    @Inject
    private OppositeStationSixListener oppositeStationSixListener;

    @Inject
    private OppositeStationTenListener oppositeStationTenListener;


    @Inject
    private OppositeStationThreeListener oppositeStationThreeListener;

    @Inject
    private OppositeStationTwelveListener oppositeStationTwelveListener;

    @Inject
    private OppositeStationTwoListener oppositeStationTwoListener;


    public OpcUaClientTemplate getOpcUaClientTemplate() {
        return opcUaClientTemplate;
    }

    public void setOpcUaClientTemplate(OpcUaClientTemplate opcUaClientTemplate) {
        this.opcUaClientTemplate = opcUaClientTemplate;
    }

    public OpcUaSubscribeNodes getOpcUaSubscribeNodes() {
        return opcUaSubscribeNodes;
    }

    public void setOpcUaSubscribeNodes(OpcUaSubscribeNodes opcUaSubscribeNodes) {
        this.opcUaSubscribeNodes = opcUaSubscribeNodes;
    }

    @Override
    public void onConnected(int plcNo, UaClient uaClient) {
// opcua 连接成功
        LOGGER.info("---------->>>>> opcua client connect success <<<<<-----------");
        Map<MonitoredDataItemListener, List<String>> listenerMap = new HashMap<>();
        switch (plcNo) {
            case 0:
                listenerMap.put(positiveStationOneListener, opcUaSubscribeNodes.getPositiveStationOneSubscribeNodes());
                listenerMap.put(positiveStationFiveListener, opcUaSubscribeNodes.getPositiveStationFiveSubscribeNodes());
                listenerMap.put(positiveStationSixListener, opcUaSubscribeNodes.getPositiveStationSixSubscribeNodes());
                listenerMap.put(positiveStationSevenListener, opcUaSubscribeNodes.getPositiveStationSevenSubscribeNodes());
                break;
            case 1:
                listenerMap.put(weldJointStationOneListener, opcUaSubscribeNodes.getWeldJointStationOneSubscribeNodes());
                listenerMap.put(weldJointStationTwoListener, opcUaSubscribeNodes.getWeldJointStationTwoSubscribeNodes());
                listenerMap.put(oppositeStationOneListener, opcUaSubscribeNodes.getOppositeStationOneSubscribeNodes());
                listenerMap.put(oppositeStationTwoListener, opcUaSubscribeNodes.getOppositeStationTwoSubscribeNodes());
                break;
            case 2:
                listenerMap.put(oppositeStationThreeListener, opcUaSubscribeNodes.getOppositeStationThreeSubscribeNodes());
                listenerMap.put(oppositeStationFourListener, opcUaSubscribeNodes.getOppositeStationFourSubscribeNodes());
                listenerMap.put(oppositeStationFiveListener, opcUaSubscribeNodes.getOppositeStationFiveSubscribeNodes());
                listenerMap.put(oppositeStationSixListener, opcUaSubscribeNodes.getOppositeStationSixSubscribeNodes());
                break;
            case 3:
                listenerMap.put(oppositeStationSevenListener, opcUaSubscribeNodes.getOppositeStationSevenSubscribeNodes());
                listenerMap.put(oppositeStationEightListener, opcUaSubscribeNodes.getOppositeStationEightSubscribeNodes());
                listenerMap.put(oppositeStationNineListener, opcUaSubscribeNodes.getOppositeStationNineSubscribeNodes());
                listenerMap.put(oppositeStationTenListener, opcUaSubscribeNodes.getOppositeStationTenSubscribeNodes());
                break;
            case 4:
                listenerMap.put(oppositeStationElevenListener, opcUaSubscribeNodes.getOppositeStationElevenSubscribeNodes());
                listenerMap.put(oppositeStationTwelveListener, opcUaSubscribeNodes.getOppositeStationTwelveSubscribeNodes());
                break;
        }
        subscribeNodesValue(uaClient, listenerMap);
    }

    private synchronized void subscribeNodesValue(UaClient uaClient, Map<MonitoredDataItemListener, List<String>> map) {
        try {
            for (Map.Entry<MonitoredDataItemListener, List<String>> entry : map.entrySet()) {
                MonitoredDataItemListener listener = entry.getKey();
                List<String> strList = entry.getValue();
                for (String nodeIdStr : strList) {
                    if (LISTENER_MAP.containsKey(nodeIdStr + ":" + listener.getClass().toString())) {
                        return;
                    }
                    LOGGER.debug("add listener:{}", nodeIdStr + ":" + listener.getClass().toString());
                    //执行订阅
                    opcUaClientTemplate.subscribeNodeValue(uaClient, new NodeId(3, nodeIdStr), listener);
                    LISTENER_MAP.put(nodeIdStr + ":" + listener.getClass().toString(), listener);
                }
            }
        } catch (OpcUaClientException e) {
            LOGGER.error("OpcUa Client Exception when subscribeNodesValue", e);
        }
    }
}
