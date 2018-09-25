package day01_02;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class OldConsumer {
    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put("zookeeper.connect", "s102:2181,s103:2181,s104:2181");
        prop.put("group.id", "g4");
        prop.put("auto.commit.enable","false");
        prop.put("auto.offset.reset","smallest");
        //prop.put("consumer.timeout.ms","10000");

        ConsumerConfig config = new ConsumerConfig(prop);

        //创建消费者java连接器
        ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);

        //String：主题
        //int: 消费线程数
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("t2", 1);

        //通过连接器获取消息流
        //String:主题
        //list：kafka消息流
        Map<String, List<KafkaStream<byte[], byte[]>>> message = connector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streamList = message.get("t2");
        //迭代list
        for (KafkaStream<byte[], byte[]> stream : streamList) {
            //获取迭代器
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            //通过while迭代所有数据
            while (it.hasNext()) {
                MessageAndMetadata<byte[], byte[]> next = it.next();
                System.out.println( /**"key:"+new String(next.key()) + "\t" + **/"value:"+ new String(next.message()));
                connector.commitOffsets();
            }
        }
    }
}
