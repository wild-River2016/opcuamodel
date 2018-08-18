package com.mj.k4.opcua.ualistener;

import com.mj.common.util.time.ResultVM;
import com.mj.common.util.time.Time;
import com.mj.k4.config.websocket.WebSocketService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/5/8
 * Annotation:
 */
@Slf4j
@Component
public class PositiveStationOneListener implements MonitoredDataItemListener {

    @Inject
    private TaskExecutor taskExecutor;

    @Inject
    private OpcUaClientTemplate opcUaClientTemplate;

    @Inject
    private WebSocketService webSocketService;


    @Async("taskExecutor")
    private void positiveStationOne(MonitoredDataItem monitoredDataItem, DataValue dataValue, DataValue dataValue1) {
        if (dataValue != null && dataValue.getValue().getValue() != dataValue1.getValue().getValue() && (boolean) dataValue1.getValue().getValue()) {
            log.info("正面工位1急停被触发");
        }
        if (dataValue != null && dataValue.getValue().getValue() != dataValue1.getValue().getValue() && (boolean) dataValue.getValue().getValue() && !(boolean) dataValue1.getValue().getValue()) {
            log.info("正面工位1急停恢复被触发");
        }
    }

    @Override
    public void onDataChange(MonitoredDataItem monitoredDataItem, DataValue dataValue, DataValue dataValue1) {
        log.info(" 正面工位1被触发 AffuseMachineListener monitoredDataItem= " + monitoredDataItem + ";dataValue=" + dataValue + ";dataValue1=" + dataValue1);
        taskExecutor.execute(() -> positiveStationOne(monitoredDataItem, dataValue, dataValue1));
    }


}
