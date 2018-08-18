package com.mj.k4.opcua;

/**
 * @author liumin
 */
public class OpcUaClientException extends Exception {

    public OpcUaClientException() {
        super();
    }

    public OpcUaClientException(String message) {
        super(message);
    }

    public OpcUaClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
