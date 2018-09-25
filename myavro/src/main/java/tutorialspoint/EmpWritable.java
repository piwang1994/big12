package tutorialspoint;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EmpWritable implements Writable {

    private Emp emp;

    public Emp getEmp() {
        return emp;
    }

    public void setEmp(Emp emp) {
        this.emp = emp;
    }

    public void write(DataOutput out) throws IOException {
        out.writeInt(emp.getId());
        out.writeUTF((String)emp.getName());
        out.writeInt(emp.getAge());
        out.writeInt(emp.getSalary());
        out.writeUTF((String)emp.getAddress());
    }

    public void readFields(DataInput in) throws IOException {
        emp = new Emp();
        emp.setId( in.readInt());
        emp.setName(in.readUTF());
        emp.setAge(in.readInt());
        emp.setSalary(in.readInt());
        emp.setAddress(in.readUTF());

    }
}
