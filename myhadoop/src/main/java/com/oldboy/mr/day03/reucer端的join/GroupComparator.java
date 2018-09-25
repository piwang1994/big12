package com.oldboy.mr.day03.reucer端的join;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class GroupComparator extends WritableComparator {

    public GroupComparator() {

        super(CompKey.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        CompKey ck1 = (CompKey) a;
        CompKey ck2 = (CompKey) b;

        return ck1.getId().compareTo(ck2.getId());


    }
}
