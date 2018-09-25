package com.co.common;

/**
 * 应用上报的事件相关信息
 */
public class AppEventLog extends AppBaseLog {


    private String eventId;         //事件唯一标识，包括用户对特定音乐的操作，比如分享，收藏，主动播放，听完，跳过，取消收藏，拉黑

    private String musicID;         //歌曲名称

    private String playTime;        //什么时刻播放
    private String duration;        //播放时长，如果播放时长在30s之内则判定为跳过
    private String mark;            //打分，分享4分,收藏3分，主动播放2分，听完1分，跳过-1分，取消收藏-4，拉黑-5分



    public AppEventLog() { setLogType(LOGTYPE_EVENT); }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getMusicID() {
        return musicID;
    }

    public void setMusicID(String musicID) {
        this.musicID = musicID;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

}
