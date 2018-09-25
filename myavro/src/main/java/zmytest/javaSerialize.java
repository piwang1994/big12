package zmytest;

import org.junit.Test;

import java.io.*;

public class javaSerialize {

    @Test
    public void Ser() throws IOException {
        Emp emp = new Emp();
        emp.setName("wang");
        emp.setId(1);
        emp.setAge(10);
        emp.setSalary(20000);
        emp.setAddress("changping");

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\avro\\fuxi\\1.java"));
        oos.writeObject(emp);

        oos.close();

    }

    @Test
    public void Deser() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/avro/fuxi/1.java"));
        Object o = ois.readObject();
        Emp o1 = (Emp) o;
        System.out.print(o1);



    }
}
