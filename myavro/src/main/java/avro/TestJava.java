package avro;

import org.junit.Test;
import tutorialspoint.Emp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestJava {

    @Test
    public void testSerial() throws Exception{

        Emp emp = new Emp();

        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);
        emp.setSalary(20000);
        emp.setAddress("昌平");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/avro/emp.java"));

        oos.writeObject(emp);

        oos.close();

    }

    @Test
    public void testDeSerial() throws Exception{

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/avro/emp.java"));

        Emp emp = (Emp)ois.readObject();

        System.out.println(emp);

        ois.close();

    }
}
