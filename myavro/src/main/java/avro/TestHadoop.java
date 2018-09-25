package avro;

import org.junit.Test;
import tutorialspoint.Emp;
import tutorialspoint.EmpWritable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestHadoop {

    @Test
    public void testSerial() throws Exception{
        Emp emp = new Emp();

        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);
        emp.setSalary(20000);
        emp.setAddress("昌平");

        EmpWritable ew = new EmpWritable();
        ew.setEmp(emp);
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:/avro/emp.hadoop"));
        ew.write(dos);
        dos.close();
    }

    @Test
    public void testDeSerial() throws Exception{
        DataInputStream dis = new DataInputStream(new FileInputStream("E:/avro/emp.hadoop"));
        EmpWritable em = new EmpWritable();
        em.readFields(dis);
        Emp emp = em.getEmp();
        dis.close();

    }

}
