package com.oldboy.mr.day03.二次排序;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * reduce端 分组对比器，自定义key业务逻辑，将1902 20 和1902 30 识别为一个key
 */
public class MyGroupComparator extends WritableComparator {

    //必须写，创建实例必须写true
    protected MyGroupComparator() {

        super(CompKey.class, true);
    }

    //比较算法
    //只要year相等则证明两个key相等
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CompKey ck1 = (CompKey) a;
        CompKey ck2 = (CompKey) b;

        return ck1.getYear().compareTo(ck2.getYear());
    }

}
