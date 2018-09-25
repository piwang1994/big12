package com.oldboy.mr.day05.用户画像;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Comkey implements WritableComparable<Comkey> {


    private int user_id;
    private int app_id;
    public Comkey() {
    }

    public Comkey(int user_id, int app_id) {
        this.user_id = user_id;
        this.app_id = app_id;
    }

    public int compareTo(Comkey o) {
        if(this.user_id==o.user_id){
            return this.user_id-o.user_id;
        }

        return this.app_id-o.app_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setApp_id(int app_id) {
        this.app_id = app_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getApp_id() {
        return app_id;
    }

    public void write(DataOutput out) throws IOException {

        out.writeInt(app_id);
        out.writeInt(user_id);
    }

    public void readFields(DataInput in) throws IOException {
        this.setUser_id(in.readInt());
        this.setApp_id(in.readInt());
    }




}
