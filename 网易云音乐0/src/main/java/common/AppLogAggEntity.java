package common;

import java.util.List;

/**
 * App日志聚合体,phone端程序上报日志使用
 */
public class AppLogAggEntity {
    private String deviceId;            //设备唯一标识
    private String appVersion;            //版本
    private String appChannel;            //渠道,安装时就在清单中制定了，appStore等。
    private String appPlatform;            //平台
    private String osType;                //操作系统
    private String deviceStyle;            //机型

    private List<AppStartupLog> appStartupLogs;            //启动相关信息的数组
    private List<AppPageLog> appPageLogs;                //页面跳转相关信息的数组
    private List<AppEventLog> appEventLogs;            //事件相关信息的数组
    private List<AppUsageLog> appUsageLogs;            //app使用情况相关信息的数组
    private List<AppErrorLog> appErrorLogs;            //错误相关信息的数组

//	public String getAppId() {
//		return appId;
//	}
//
//	public void setAppId(String appId) {
//		this.appId = appId;
//	}


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

    public List<AppStartupLog> getAppStartupLogs() {
        return appStartupLogs;
    }

    public void setAppStartupLogs(List<AppStartupLog> appStartupLogs) {
        this.appStartupLogs = appStartupLogs;
    }

    public List<AppPageLog> getAppPageLogs() {
        return appPageLogs;
    }

    public void setAppPageLogs(List<AppPageLog> appPageLogs) {
        this.appPageLogs = appPageLogs;
    }

    public List<AppEventLog> getAppEventLogs() {
        return appEventLogs;
    }

    public void setAppEventLogs(List<AppEventLog> appEventLogs) {
        this.appEventLogs = appEventLogs;
    }

    public List<AppUsageLog> getAppUsageLogs() {
        return appUsageLogs;
    }

    public void setAppUsageLogs(List<AppUsageLog> appUsageLogs) {
        this.appUsageLogs = appUsageLogs;
    }

    public List<AppErrorLog> getAppErrorLogs() {
        return appErrorLogs;
    }

    public void setAppErrorLogs(List<AppErrorLog> appErrorLogs) {
        this.appErrorLogs = appErrorLogs;
    }
}
