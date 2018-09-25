package com.oldboy.log.common;

/**
 * errorLog
 * 分析用户对手机App使用过程中的错误
 * 以便对产品进行调整
 */
public class AppErrorLog extends AppBaseLog {

    private String errorBrief;        //错误摘要
    private String errorDetail;       //错误详情

    public String getErrorBrief() {
        return errorBrief;
    }

    public void setErrorBrief(String errorBrief) {
        this.errorBrief = errorBrief;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
