package day01_01;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;
import java.util.Random;

public class TestProducer {
    public static void main(String[] args) throws Exception {

        //构造java配置文件
        Properties prop = new Properties();
        prop.put("metadata.broker.list","s202:9092,s203:9092,s204:9092");
        prop.put("serializer.class","kafka.serializer.StringEncoder");
        prop.put("key.serializer.class","kafka.serializer.StringEncoder");
        prop.put("producer.type","async");
        prop.put("request.required.acks","0");
        //定义分区函数
        prop.put("partitioner.class","day01_01.MyPartition");
        //prop.put("batch.num.messages","1000");;
        //prop.put("queue.buffer.max.ms","5000");

        //通过java配置文件初始化producer配置文件
        ProducerConfig config = new ProducerConfig(prop);

        //通过producer配置文件，初始化producer
        Producer<String, String> producer = new Producer<String, String>(config);

        String[] arr = {"a","b","c","d"};
        Random r = new Random();

        long start = System.currentTimeMillis();
        //构造message数据(包括k-v)KeyedMessage<topic,message>
        for (int i = 1; i < 100000; i++) {
            KeyedMessage<String, String> msg = new KeyedMessage<String, String>("t1",  arr[r.nextInt(4)],"tom"+i);
            producer.send(msg);
            Thread.sleep(500);
        }
        System.out.println(System.currentTimeMillis() - start);

        producer.close();
    }
}
