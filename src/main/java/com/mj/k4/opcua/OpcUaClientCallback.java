package com.mj.k4.opcua;

/**
 * @author liumin
 */
public interface OpcUaClientCallback<T> {

    T performAction() throws Exception;
}
