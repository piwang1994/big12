package day02_01;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class NewConsumer2 {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers","s202:9092,s203:9092,s204:9092");
        props.put("group.id", "g3");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "100");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        //通过subscribe方法，指定多主题消费
        //consumer.subscribe(Arrays.asList("t4","t5"));


        //指定分区消费
        ArrayList<TopicPartition> list = new ArrayList<TopicPartition>();
        TopicPartition tp = new TopicPartition("t1", 0);
        TopicPartition tp2 = new TopicPartition("t4", 0);
        TopicPartition tp3 = new TopicPartition("t4", 1);
        TopicPartition tp4 = new TopicPartition("t4", 2);
        list.add(tp);
        list.add(tp2);
        list.add(tp3);
        list.add(tp4);
        consumer.assign(list);

        //--------------------------------------------------------------------------------------------------------
//        Map<TopicPartition, OffsetAndMetadata> offset = new HashMap<TopicPartition, OffsetAndMetadata>();
//        //指定分区
//        TopicPartition tp = new TopicPartition("t4", 0);
//        //指定偏移量
//        OffsetAndMetadata metadata = new OffsetAndMetadata(0);
//        offset.put(tp,metadata);
//        //修改偏移量
//        consumer.commitSync(offset);
//        //订阅主题
//        consumer.subscribe(Arrays.asList("t4"));

        //------------------------------------------------------------------------------------------------------------
        //指定分区
//        TopicPartition tp = new TopicPartition("t4", 0);
//        consumer.assign(Arrays.asList(tp));
        //修改搜索指针
//        consumer.seek(tp,10);
//        consumer.wakeup();


        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(5000);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                consumer.commitSync();
        }
    }
}
