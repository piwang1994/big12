import org.apache.flume.*;
import org.apache.flume.event.EventHelper;
import org.apache.flume.sink.AbstractSink;

import java.util.Map;

public class MySink extends AbstractSink {


    public Status process() throws EventDeliveryException {
        Status result = Status.READY;
        Channel channel = getChannel();
        Transaction transaction = channel.getTransaction();
        Event event = null;

        try {
            transaction.begin();
            //从通道中获取数据
            event = channel.take();

            if (event != null) {
                StringBuffer sb = new StringBuffer();
                Map<String, String> map = event.getHeaders();

                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    sb.append(key+":"+value+",");
                }
                String body = new String(event.getBody());
                String headers = sb.toString();
                String newStr = "";
                if(headers.length() != 0){
                    newStr = headers.substring(0, headers.length() - 1);
                }
                System.out.println("头部=" + newStr + "\t" + "身体=" + body);

            } else {
                // No event found, request back-off semantics from the sink runner
                result = Status.BACKOFF;
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new EventDeliveryException("Failed to log event: " + event, ex);
        } finally {
            transaction.close();
        }

        return result;
    }
}
