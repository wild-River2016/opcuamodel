package com.mj.k4.opcua;

import com.prosysopc.ua.client.UaClient;

/**
 * @author liumin
 */
public interface OpcUaClientFactory {

    UaClient createUaClient() throws OpcUaClientException;

    String getUaAddress();
}
