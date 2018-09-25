package com.oldboy.log.common;

/**
 * 应用上报的页面相关信息
 */
public class AppPageLog extends AppBaseLog {

    /*
     * 一次启动中的页面访问次数(应保证每次启动的所有页面日志在一次上报中，即最后一条上报的页面记录的nextPage为空)
     */
    private int pageViewCntInSession = 0;

    private String pageId;          //页面id
    private String visitIndex;      //访问顺序号，0为第一个页面
    private String nextPage;        //下一个访问页面，如为空则表示为退出应用的页面
    private String stayDurationSecs;  //当前页面停留时长

    public AppPageLog() {
        setLogType(LOGTYPE_PAGE);
    }

    public int getPageViewCntInSession() {
        return pageViewCntInSession;
    }

    public void setPageViewCntInSession(int pageViewCntInSession) {
        this.pageViewCntInSession = pageViewCntInSession;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getVisitIndex() {
        return visitIndex;
    }

    public void setVisitIndex(String visitIndex) {
        this.visitIndex = visitIndex;
    }

    public String getStayDurationSecs() {
        return stayDurationSecs;
    }

    public void setStayDurationSecs(String stayDurationSecs) {
        this.stayDurationSecs = stayDurationSecs;
    }
}
