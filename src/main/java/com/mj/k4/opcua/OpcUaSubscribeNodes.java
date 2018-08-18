package com.mj.k4.opcua;

import com.mj.k4.opcua.config.OpcUaProperties;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SunYi on 2016/3/7/0007.
 */
@Data
public class OpcUaSubscribeNodes {
    private List<String> positiveStationOneSubscribeNodes;
    private List<String> positiveStationFiveSubscribeNodes;
    private List<String> positiveStationSixSubscribeNodes;
    private List<String> positiveStationSevenSubscribeNodes;
    private List<String> weldJointStationOneSubscribeNodes;
    private List<String> weldJointStationTwoSubscribeNodes;
    private List<String> oppositeStationOneSubscribeNodes;
    private List<String> oppositeStationTwoSubscribeNodes;
    private List<String> oppositeStationThreeSubscribeNodes;
    private List<String> oppositeStationFourSubscribeNodes;
    private List<String> oppositeStationFiveSubscribeNodes;
    private List<String> oppositeStationSixSubscribeNodes;
    private List<String> oppositeStationSevenSubscribeNodes;
    private List<String> oppositeStationEightSubscribeNodes;
    private List<String> oppositeStationNineSubscribeNodes;
    private List<String> oppositeStationTenSubscribeNodes;
    private List<String> oppositeStationElevenSubscribeNodes;
    private List<String> oppositeStationTwelveSubscribeNodes;

    private List<String>  getRealSubscribeNodesList(String node){
        List<String> nodeList=new ArrayList<>();
        if(node !=null || node.contains("NodeBase")){
            String[]  machineNoList=new String[0];
            String[] strList = node.split("-");
            String  nodeBase=strList[0].trim().substring(strList[0].indexOf("#")+1).trim();
            if (node.contains("No#")) {
                machineNoList = strList[1].trim().substring(strList[1].trim().indexOf("#") + 1).split(",");
            }
            String[] subVarList = strList[strList.length-1].trim().substring(strList[strList.length - 1].trim().indexOf("#") + 1).split(",");
            if( machineNoList.length != 0 ) {
                for(String machineNo : machineNoList) {
                    for(String subvar : subVarList) {
                        nodeList.add(nodeBase + machineNo.trim() + "." + subvar.trim());
                    }
                }
            } else {
                for(String subvar : subVarList) {
                    nodeList.add(nodeBase + "." + subvar.trim());
                }
            }
        }
        System.err.println("OPC-UA 解析节点表  :"+nodeList);
        return nodeList;
    };

    public OpcUaSubscribeNodes(OpcUaProperties opcUaProperties) {
        List<Map<String, String>> plcList = opcUaProperties.getPlcList();
        this.positiveStationOneSubscribeNodes = getRealSubscribeNodesList(plcList.get(0).get("positiveStationOneSubscribeNodes"));
        this.positiveStationFiveSubscribeNodes = getRealSubscribeNodesList(plcList.get(0).get("positiveStationFiveSubscribeNodes"));
        this.positiveStationSixSubscribeNodes = getRealSubscribeNodesList(plcList.get(0).get("positiveStationSixSubscribeNodes"));
        this.positiveStationSevenSubscribeNodes = getRealSubscribeNodesList(plcList.get(0).get("positiveStationSevenSubscribeNodes"));

        this.weldJointStationOneSubscribeNodes = getRealSubscribeNodesList(plcList.get(1).get("weldJointStationOneSubscribeNodes"));
        this.weldJointStationTwoSubscribeNodes = getRealSubscribeNodesList(plcList.get(1).get("weldJointStationTwoSubscribeNodes"));
        this.oppositeStationOneSubscribeNodes = getRealSubscribeNodesList(plcList.get(1).get("oppositeStationOneSubscribeNodes"));
        this.oppositeStationTwoSubscribeNodes = getRealSubscribeNodesList(plcList.get(1).get("oppositeStationTwoSubscribeNodes"));

        this.oppositeStationThreeSubscribeNodes = getRealSubscribeNodesList(plcList.get(2).get("oppositeStationThreeSubscribeNodes"));
        this.oppositeStationFourSubscribeNodes = getRealSubscribeNodesList(plcList.get(2).get("oppositeStationFourSubscribeNodes"));
        this.oppositeStationFiveSubscribeNodes = getRealSubscribeNodesList(plcList.get(2).get("oppositeStationFiveSubscribeNodes"));
        this.oppositeStationSixSubscribeNodes = getRealSubscribeNodesList(plcList.get(2).get("oppositeStationSixSubscribeNodes"));

        this.oppositeStationSevenSubscribeNodes = getRealSubscribeNodesList(plcList.get(3).get("oppositeStationSevenSubscribeNodes"));
        this.oppositeStationEightSubscribeNodes = getRealSubscribeNodesList(plcList.get(3).get("oppositeStationEightSubscribeNodes"));
        this.oppositeStationNineSubscribeNodes = getRealSubscribeNodesList(plcList.get(3).get("oppositeStationNineSubscribeNodes"));
        this.oppositeStationTenSubscribeNodes = getRealSubscribeNodesList(plcList.get(3).get("oppositeStationTenSubscribeNodes"));

        this.oppositeStationElevenSubscribeNodes = getRealSubscribeNodesList(plcList.get(4).get("oppositeStationElevenSubscribeNodes"));
        this.oppositeStationTwelveSubscribeNodes = getRealSubscribeNodesList(plcList.get(4).get("oppositeStationTwelveSubscribeNodes"));

    }
}
