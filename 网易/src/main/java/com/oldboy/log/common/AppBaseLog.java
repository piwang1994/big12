package com.oldboy.log.common;

import java.io.Serializable;

/**
 * 日志基础类
 */
public abstract class AppBaseLog implements Serializable {
    public static final String LOGTYPE_ERROR = "error";
    public static final String LOGTYPE_EVENT = "event";
    public static final String LOGTYPE_PAGE = "page";
    public static final String LOGTYPE_USAGE = "usage";
    public static final String LOGTYPE_STARTUP = "startup";

    private String logType;                //日志类型
    private Long createdAtMs;           //日志创建时间
    private String deviceId;            //设备唯一标识
    private String appVersion;          //App版本
    private String appChannel;          //渠道,安装时就在清单中制定了，appStore等。
    private String appPlatform;         //平台
    private String osType;              //操作系统
    private String deviceStyle;         //机型

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getCreatedAtMs() {
        return createdAtMs;
    }

    public void setCreatedAtMs(Long createdAtMs) {
        this.createdAtMs = createdAtMs;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public void setAppChannel(String appChannel) {
        this.appChannel = appChannel;
    }

    public String getAppPlatform() {
        return appPlatform;
    }

    public void setAppPlatform(String appPlatform) {
        this.appPlatform = appPlatform;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceStyle() {
        return deviceStyle;
    }

    public void setDeviceStyle(String deviceStyle) {
        this.deviceStyle = deviceStyle;
    }
}
