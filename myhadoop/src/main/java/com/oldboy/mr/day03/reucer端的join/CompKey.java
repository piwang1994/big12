package com.oldboy.mr.day03.reucer端的join;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompKey implements WritableComparable<CompKey> {


    private int flag;
    private String id;

    //定义比较逻辑
    public int compareTo(CompKey o) {
        //如果id相同则按flag顺序排列
        if(this.id.equals(o.id)){
            return this.flag - o.flag;
        }
        return this.id.compareTo(o.id);

    }

    //串行化
    public void write(DataOutput out) throws IOException {
        out.writeInt(flag);
        out.writeUTF(id);
    }


    //反串行化
    public void readFields(DataInput in) throws IOException {
        flag = in.readInt();
        id = in.readUTF();

    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CompKey(int flag, String id) {
        this.flag = flag;
        this.id = id;
    }

    public CompKey() {
    }

    @Override
    public String toString() {
        return "CompKey{" +
                "flag=" + flag +
                ", id='" + id + '\'' +
                '}';
    }

    //保证 相同id的数据进入同一个先进入同一个partionerh后进入reduce
    @Override
    public int hashCode() {
        return Integer.parseInt(id);
    }
}
