package com.mj.k4.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by：mingwang
 * Company：MJ
 * Date：2018/3/13
 * Annotation:
 */
public class MapModbus {
    private Map<Integer, Number> mapOne;//这是一段的节点
    private Map<Integer, Number> mapTwo;//这是二段的节点
    private Map<Integer, Number> mapThree;//这是三段的节点

    private Map<Integer, Number> copyMapOne;//这是一段复制的节点
    private Map<Integer, Number> copyMapTwo;//这是二段的复制节点
    private Map<Integer, Number> copyMapThree;//这是三段复制的节点


    public MapModbus() {
        mapOne = new HashMap<>();
        mapOne.put(0, 0);
        mapOne.put(1, 0);
        mapOne.put(2, 0);
        mapOne.put(3, 0);
        mapOne.put(4, 0);
        mapOne.put(5, 0);

        mapTwo = new HashMap<>();
        mapThree = new HashMap<>();

        copyMapOne = new HashMap<>();
        copyMapOne.putAll(mapOne);
    }


    public Map<Integer, Number> getMapOne() {
        return mapOne;
    }

    public void setMapOne(Map<Integer, Number> mapOne) {
        this.mapOne = mapOne;
    }

    public Map<Integer, Number> getMapTwo() {
        return mapTwo;
    }

    public void setMapTwo(Map<Integer, Number> mapTwo) {
        this.mapTwo = mapTwo;
    }

    public Map<Integer, Number> getMapThree() {
        return mapThree;
    }

    public void setMapThree(Map<Integer, Number> mapThree) {
        this.mapThree = mapThree;
    }

    public Map<Integer, Number> getCopyMapOne() {
        return copyMapOne;
    }

    public void setCopyMapOne(Map<Integer, Number> copyMapOne) {
        this.copyMapOne = copyMapOne;
    }

    public Map<Integer, Number> getCopyMapTwo() {
        return copyMapTwo;
    }

    public void setCopyMapTwo(Map<Integer, Number> copyMapTwo) {
        this.copyMapTwo = copyMapTwo;
    }

    public Map<Integer, Number> getCopyMapThree() {
        return copyMapThree;
    }

    public void setCopyMapThree(Map<Integer, Number> copyMapThree) {
        this.copyMapThree = copyMapThree;
    }

    /**
     * 下面是提供方法进行修改
     *
     * @param key
     * @param value
     */
    public void setMapNumOne(Integer key, Number value) {
        mapOne.put(key, value);
    }

    public void setMapNumTwo(Integer key, Number value) {
        mapTwo.put(key, value);
    }

    public void setMapNumThree(Integer key, Number value) {
        mapThree.put(key, value);
    }

    public void setMapNumOneCopy(Integer key, Number value) {
        copyMapOne.put(key, value);
    }

    public void setMapNumTwoCopy(Integer key, Number value) {
        copyMapTwo.put(key, value);
    }

    public void setMapNumThreeCopy(Integer key, Number value) {
        copyMapThree.put(key, value);
    }


}
