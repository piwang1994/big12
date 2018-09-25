package com.oldboy.hdfs.sequencefile;

import com.hadoop.compression.lzo.LzoCodec;
import com.hadoop.compression.lzo.LzoIndexer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class TestLzoIndex {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        //压缩编解码器必须是此类或其子类
        conf.set("io.compression.codecs","com.hadoop.compression.lzo.LzopCodec");

        LzoIndexer indexer = new LzoIndexer(conf);

        indexer.index(new Path("file:///D:/codec/1.xml.lzo"));
    }
}
