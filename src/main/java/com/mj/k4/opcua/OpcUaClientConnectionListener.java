package com.mj.k4.opcua;

import com.prosysopc.ua.client.UaClient;

/**
 * @author liumin
 */
public interface OpcUaClientConnectionListener {

    void onConnected(int plcNo, UaClient uaClient);
}
