package com.alibaba.webx.tutorial.app1.domain;

/**
 * Created by tsiang on 2017/5/27.
 */
public class User {
    private String ip;
    private String time;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
