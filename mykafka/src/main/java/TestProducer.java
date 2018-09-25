import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class TestProducer {
    public static void main(String[] args) {

        //构造java配置文件
        Properties prop = new Properties();
        prop.put("metadata.broker.list","s202:9092,s203:9092,s204:9092");
        prop.put("serializer.class","kafka.serializer.StringEncoder");

        //通过java配置文件初始化producer配置文件
        ProducerConfig config = new ProducerConfig(prop);

        //通过producer配置文件，初始化producer
        Producer<String, String> producer = new Producer<String, String>(config);

        //构造message数据(包括k-v)KeyedMessage<topic,message>
        for (int i = 1; i < 10000; i++) {
            KeyedMessage<String, String> msg = new KeyedMessage<String, String>("t1", "tom" + i);
            producer.send(msg);
        }

        producer.close();
    }
}
