package day02_01;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class NewPartition implements Partitioner {

    //对于新分区函数，可以指定value进行分区
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //获取分区个数
        List<PartitionInfo> parts = cluster.partitionsForTopic(topic);
        int numPartition = parts.size();

        //将key的hashcode取正值，和分区数取余
        return Math.abs(key.hashCode()) % numPartition;

    }

    public void close() {

    }

    public void configure(Map<String, ?> configs) {

    }
}
