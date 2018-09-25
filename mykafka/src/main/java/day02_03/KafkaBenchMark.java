package day02_03;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaBenchMark {
    static Producer<String, byte[]> producer;
    static Properties props= new Properties();
    static {
        props.put("bootstrap.servers", "s202:9092,s203:9092,s204:9092");
        props.put("acks", "0");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        producer = new KafkaProducer<String, byte[]>(props);
    }
    public static void main(String[] args) throws Exception {

        Mythread m1 = new Mythread();
        Mythread m2 = new Mythread();
        Mythread m3 = new Mythread();

        m1.start();
        m2.start();
        m3.start();



    }

    public void Test() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1024 * 1024; i++) {
            ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("t12", new byte[1024]);
            //异步状态

            producer.send(record).get();
        }
        System.out.println(System.currentTimeMillis() - start);
        producer.close();
    }


    public static class Mythread extends Thread{


        public void run() {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1024 * 1024/3; i++) {
                ProducerRecord<String, byte[]> record = new ProducerRecord<String, byte[]>("t12", new byte[1024]);
                //异步状态
                producer.send(record);
            }
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}
