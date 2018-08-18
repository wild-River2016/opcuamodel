package com.mj.k4.opcua.ualistener;

import com.mj.k4.config.websocket.WebSocketService;
import com.mj.k4.dto.StationDBDTO;
import com.mj.k4.opcua.OpcUaClientException;
import com.mj.k4.opcua.OpcUaClientTemplate;
import com.prosysopc.ua.client.MonitoredDataItem;
import com.prosysopc.ua.client.MonitoredDataItemListener;
import lombok.extern.slf4j.Slf4j;
import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/5/31
 * Annotation:
 */
@Slf4j
@Component
public class OppositeStationElevenListener implements MonitoredDataItemListener {

    @Inject
    private TaskExecutor taskExecutor;

    @Inject
    private OpcUaClientTemplate opcUaClientTemplate;

    @Inject
    private WebSocketService webSocketService;


    @Async("taskExecutor")
    private void oppositeStationElevenWebsocket(MonitoredDataItem monitoredDataItem, DataValue dataValue, DataValue dataValue1) {
        if (dataValue != null && dataValue.getValue().getValue() != dataValue1.getValue().getValue() && true == (boolean) dataValue1.getValue().getValue()) {
            List<Integer> stationList = new ArrayList<>();
            try {
                for (int i = 38; i < 46; i++) {
                    NodeId nodeId = new NodeId(3, "\"OPCUA\".\"OPCEA_DATA\"[" + i + "]");
                    Variant v = opcUaClientTemplate.readNodeVariant(opcUaClientTemplate.getUaClientList().get(0), nodeId);
                    if (null != v.getValue() && !v.getValue().equals("")) {
                        stationList.add((Integer) v.getValue());
                    } else {
                        stationList.add(1);
                    }
                }
                StationDBDTO.STATION = stationList;
                webSocketService.sendInfo("MainBoard", StationDBDTO.ROBOT);
            } catch (OpcUaClientException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDataChange(MonitoredDataItem monitoredDataItem, DataValue dataValue, DataValue dataValue1) {
        log.info(" 人工工位被触发 AffuseMachineListener monitoredDataItem= " + monitoredDataItem + ";dataValue=" + dataValue + ";dataValue1=" + dataValue1);
        taskExecutor.execute(() -> oppositeStationElevenWebsocket(monitoredDataItem, dataValue, dataValue1));
    }
}
