package com.oldboy.mr.day05.top10;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompKey implements WritableComparable<CompKey> {

    private String pass;
    private int sum;

    //对频数进行倒排序
    public int compareTo(CompKey o) {

        if(this.sum == o.sum){
            return this.pass.compareTo(o.pass);
        }
        return o.sum-this.sum;
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(pass);
        out.writeInt(sum);
    }

    public void readFields(DataInput in) throws IOException {
        pass = in.readUTF();
        sum = in.readInt();

    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public CompKey(String pass, int sum) {
        this.pass = pass;
        this.sum = sum;
    }

    public CompKey() {
    }

    @Override
    public String toString() {
        return pass + "\t" + sum;
    }
}
