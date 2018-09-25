import com.google.common.base.Strings;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.event.EventHelper;
import org.apache.flume.sink.AbstractSink;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyHDFSSink extends AbstractSink implements Configurable {


    //定义默认文件名
    public static final String DEFAULT_FILE_NAME = "/1.txt";
    //定义参数关键字
    public static final String FILE_NAME = "filename";

    private String filename;


    public void configure(Context context) {
        //通过参数关键字获取到定义的文件名
        filename = context.getString(FILE_NAME, DEFAULT_FILE_NAME);
    }


    public Status process() throws EventDeliveryException {
        Status result = Status.READY;
        Channel channel = getChannel();
        Transaction transaction = channel.getTransaction();
        Event event = null;

        try {
            transaction.begin();
            event = channel.take();

            if (event != null) {

                try {
                    FileSystem fs = FileSystem.get(new Configuration());
                    Path p = new Path(filename);
                    if (fs.exists(p)) {
                        FSDataOutputStream fos = fs.append(p);
                        fos.write(event.getBody());
                        fos.write("\n".getBytes());
                        fos.close();
                        System.out.println("事件发送成功");
                    } else {
                        FSDataOutputStream fos = fs.create(p);
                        fos.write(event.getBody());
                        fos.write("\n".getBytes());
                        fos.close();
                        System.out.println("事件发送成功");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
