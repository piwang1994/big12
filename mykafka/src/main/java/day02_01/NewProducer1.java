package day02_01;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class NewProducer1 {
    public static void main(String[] args) throws Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", "s202:9092,s203:9092,s204:9092");
        props.put("acks", "0");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class","day02_01.NewPartition");

        long start = System.currentTimeMillis();
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100000; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("t4", Integer.toString(i), Integer.toString(i));
            System.out.println("key:" + i + "\t" + Math.abs(Integer.toString(i).hashCode()));
            System.out.println("===========================================");
            Thread.sleep(500);
            producer.send(record);
        }
        System.out.println(System.currentTimeMillis() - start);
        producer.close();
    }
}
