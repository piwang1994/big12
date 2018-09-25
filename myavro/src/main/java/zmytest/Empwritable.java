package zmytest;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Empwritable implements Writable {
    private  Emp emp;

    //   public  Empwritable(String name ,int id,int salary,int age,String addr){

  //  }


    public Empwritable(Emp emp){
        this.emp=emp;
    }

    public Empwritable() {

    }


    public Emp getEmp(){
        return this.emp;
    }

    public void setEmp(Emp emp){
        this.emp=emp;
    }


    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF((String) this.emp.getName());
        dataOutput.writeInt(this.emp.getId());
        dataOutput.writeInt(this.emp.getSalary());
        dataOutput.writeInt(this.emp.getAge());
        dataOutput.writeUTF((String)this.emp.getAddress());

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        emp = new Emp();

        emp.setName(dataInput.readUTF());
        emp.setId(dataInput.readInt());
        emp.setSalary(dataInput.readInt())  ;
        emp.setAge(dataInput.readInt());
        emp.setAddress(dataInput.readUTF());

    }
}
