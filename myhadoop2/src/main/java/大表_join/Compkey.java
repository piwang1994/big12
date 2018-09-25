package 大表_join;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Compkey implements WritableComparable<Compkey> {
    private int flag;
    private String id;

    public Compkey(int flag, String id) {
        this.flag = flag;
        this.id = id;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int compareTo(Compkey o) {
        //id 顺序相同则按flag升序
        //id 的顺序不同则按id升序
        if(this.id.equals(o.id)){
            return this.flag-o.flag;
        }
        return this.id.compareTo(o.id);

    }

    public void write(DataOutput out) throws IOException {
        out.write(flag);
        out.writeUTF(id);

    }

    public void readFields(DataInput in) throws IOException {

        int flag = in.readInt();
        String id = in.readUTF();
    }


    @Override
    public int hashCode() {
        return Integer.parseInt(this.id);
    }
}
