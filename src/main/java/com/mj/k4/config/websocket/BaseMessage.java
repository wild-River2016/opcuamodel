package com.mj.k4.config.websocket;

/**
 * Created by 范超
 * Date:2017/12/28
 */
public class BaseMessage<T> {
    private String topic;
    private T date;

    public BaseMessage(String topic, T date) {
        this.topic = topic;
        this.date = date;
    }

    public BaseMessage(T date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }
}
