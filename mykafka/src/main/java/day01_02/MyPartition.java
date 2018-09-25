package day01_02;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class MyPartition implements Partitioner {

    //必填项，不然无法通过配置文件配置
    public MyPartition (VerifiableProperties props) {
    }

    public int partition(Object key, int numPartitions) {
        if(key.toString().startsWith("a")){
            return 0;
        }
        if(key.toString().startsWith("b")){
            return 1;
        }
        else {
            return 2;
        }
    }
}
