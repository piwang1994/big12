package com.oldboy.log.common;

/**
 * 应用上报的使用时长相关信息
 */
public class AppUsageLog extends AppBaseLog {

    private String singleUseDurationSecs;        //单次使用时长(秒数),指一次启动内应用在前台的持续时长
    private String singleUploadTraffic;          //单次使用过程中的上传流量
    private String singleDownloadTraffic;        //单次使用过程中的下载流量

    public AppUsageLog() {
        setLogType(LOGTYPE_USAGE);
    }

    public String getSingleUseDurationSecs() {
        return singleUseDurationSecs;
    }

    public void setSingleUseDurationSecs(String singleUseDurationSecs) {
        this.singleUseDurationSecs = singleUseDurationSecs;
    }

    public String getSingleUploadTraffic() {
        return singleUploadTraffic;
    }

    public void setSingleUploadTraffic(String singleUploadTraffic) {
        this.singleUploadTraffic = singleUploadTraffic;
    }

    public String getSingleDownloadTraffic() {
        return singleDownloadTraffic;
    }

    public void setSingleDownloadTraffic(String singleDownloadTraffic) {
        this.singleDownloadTraffic = singleDownloadTraffic;
    }
}
