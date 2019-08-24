package com.pjq.pojo;

import java.util.Date;

public class Reply {

    private String reply_name;
    private String reply_content;
    private Date time;

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public String getReply_content() {
        return reply_content;
    }

    public void setReply_content(String reply_content) {
        this.reply_content = reply_content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
