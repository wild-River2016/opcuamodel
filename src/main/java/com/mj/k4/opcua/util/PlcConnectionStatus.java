package com.mj.k4.opcua.util;

import lombok.Data;

/**
 * Created by Ricardo on 2018/6/12.
 * 设置plc连接状态标识符
 */
@Data
public class PlcConnectionStatus {
    public static boolean PLC01STATUS=false;//211plc
    public static boolean PLC02STATUS=false;//213plc
    public static boolean PLC03STATUS=false;//159plc
    public static boolean PLC04STATUS=false;//189plc

}
