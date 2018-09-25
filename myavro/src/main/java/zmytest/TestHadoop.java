package zmytest;

import org.junit.Test;

import java.io.*;

public class TestHadoop {
    @Test
    public void testSerial() throws IOException {
        Emp emp = new Emp();

        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);;
        emp.setSalary(20000);
        emp.setAddress("昌平");

        DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:/avro/fuxi/1.hadoop"));
        Empwritable ew = new Empwritable(emp);
        ew.write(dos);

        dos.close();
    }

    public void testDeserial() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("E:/avro/fuxi/1.hadoop"));
        Empwritable emw = new Empwritable();
        emw.readFields(dis);
        Emp emp = emw.getEmp();
        System.out.print(emp);

    }


}
