package com.mj.k4.opcua.util;


import org.opcfoundation.ua.builtintypes.DataValue;
import org.opcfoundation.ua.builtintypes.NodeId;
import org.opcfoundation.ua.builtintypes.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiYang
 * @date 2018/4/12.
 *
 * OpcUa相关的工具类(抽象类不允许实例化)
 */
public abstract class OpcUaUtil {
    private static final Logger log = LoggerFactory.getLogger(OpcUaUtil.class);
    private  static Map<String,Object>  LAST_NODE_VALUE_MAP=new ConcurrentHashMap();
     protected static List<String> OPCUA_LIST=new ArrayList<>();
    /**
     * @param plcIndex  opcUA对应的服务的url
     * @param id  节点Id
     * @param oldDataValue 原来值
     * @param newDataValue 最新读取值
     * @return  该值是否更新,更新为true,未更新为false(排除掉第一服务器启动更新值)
     */
    public static boolean  isNewNodeValueValid(String plcIndex, NodeId id, DataValue oldDataValue, DataValue newDataValue){
        String nodeId = plcIndex + "." + id.toString();
        if (null == oldDataValue) {
            log.info("The subscription for {} is initialized.", nodeId);
            LAST_NODE_VALUE_MAP.put(nodeId, newDataValue.getValue().getValue());
            return false;
        }
        Variant oldVariant = oldDataValue.getValue();
        Variant newVariant = newDataValue.getValue();
        if (newVariant.isArray()){
            Object[] oldArray = (Object[]) oldVariant.getValue();
            Object[] newArray = (Object[]) newVariant.getValue();
            boolean flag = false;
            for (int i = 0; i < newArray.length; i++) {
                if (oldArray[i].equals(newArray[i])
                    || (LAST_NODE_VALUE_MAP.get(nodeId) != null
                    && ((Object[])LAST_NODE_VALUE_MAP.get(nodeId))[i].equals(newArray[i]))) {
                    continue;
                }
                flag = true;
                break;
            }
            if (!flag){
                log.debug("--->>" + nodeId + " from " + oldVariant + " to " + newVariant + "<<---");
                log.error("data change error: listener YOU DU!!!");
                return false;
            }
        } else {
            if (newVariant.getValue().equals(oldVariant.getValue())
                || (LAST_NODE_VALUE_MAP.get(nodeId) != null
                && LAST_NODE_VALUE_MAP.get(nodeId).equals(newVariant.getValue()))) {

                log.debug("--->>" + nodeId + " from " + oldVariant + " to " + newVariant + "<<---");
                log.error("data change error: listener YOU DU!!!");
                return false;
            }
        }
        log.info("--->>" + nodeId + " from " + oldVariant + " to " + newVariant + "<<--- go.");
        LAST_NODE_VALUE_MAP.put(nodeId, newVariant.getValue());
        return true;
    }

    /**
     * @return
     * 获取OpcUa 的Address地址链表(禁止)
     */
    public static List<String> getOpcUaAddressList(){
     return OPCUA_LIST;
    };



}

