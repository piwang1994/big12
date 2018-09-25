package com.oldboy.mr.day03.二次排序;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class CompKey implements WritableComparable<CompKey> {

    private String year;
    private int temp;

    public CompKey(String year, int temp) {
        this.year = year;
        this.temp = temp;
    }

    public CompKey() {
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    //定义排序规则
    public int compareTo(CompKey o) {

        String oyear = o.getYear();
        String tyear = this.getYear();
        int otemp = o.getTemp();
        int ttemp = this.getTemp();

        //如果参数year 和现在的year相同，则比较temp的大小
        if (tyear.equals(oyear)) {
            //年份--->
            return otemp - ttemp;
        }
        //不同，返回两个year的比较值---》
        return oyear.compareTo(tyear);
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(year);
        out.writeInt(temp);
    }

    public void readFields(DataInput in) throws IOException {
        this.setYear(in.readUTF());
        this.setTemp(in.readInt());

    }


    @Override
    public String toString() {
        return "CompKey{" +
                "year='" + year + '\'' +
                ", temp=" + temp +
                '}';
    }
}
