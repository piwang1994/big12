package avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import tutorialspoint.Emp;
import tutorialspoint.EmpWritable;

import java.io.*;

public class TestAllandCompare {

   //------------------------------------------------------------------------------------------------------------------
    @Test
    public void testJavaSerial() throws Exception {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:/avro/emp2.java"));

        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            Emp emp = new Emp();
            emp.setId(i);
            emp.setName("tom"+i);
            emp.setAge(i % 100);
            emp.setSalary(20000);
            emp.setAddress("昌平");
            oos.writeObject(emp);
        }
        System.out.println(System.currentTimeMillis() - start);  //20034ms  33,240,733 字节
        oos.close();
    }

    @Test
    public void testJavaDeSerial() throws Exception {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("E:/avro/emp2.java"));

        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            Emp emp = (Emp) ois.readObject();
//            System.out.println(emp);
        }
        System.out.println(System.currentTimeMillis() - start);
        ois.close();

    }



    //==============Avro==================================================================================================
    @Test
    public void testAvroSerial() throws IOException {
        //
    DatumWriter<Emp> dw = new SpecificDatumWriter<Emp>(Emp.class);

    //入口点，实例化DataFileWriter
    DataFileWriter<Emp> dfw = new DataFileWriter<Emp>(dw);

    //开始序列化
    //参数2：输出文件路径
        dfw.create(Emp.SCHEMA$, new File("E:/avro/emp2.avro"));

    long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
        Emp emp = new Emp();
        emp.setId(i);
        emp.setName("tom"+i);
        emp.setAge(i % 100);
        emp.setSalary(20000);
        emp.setAddress("昌平");
        dfw.append(emp);
    }
        System.out.println(System.currentTimeMillis() - start);
    //添加对象
        dfw.close();

}

    @Test
    public  void testAvroDeserial()  throws Exception{

        DatumReader<Emp> dr = new SpecificDatumReader<Emp>();
        DataFileReader<Emp> dfr = new DataFileReader<Emp>(new File("E:/avro/emp2.avro"),dr);

        long start = System.currentTimeMillis();
        while(dfr.hasNext()){
            Emp emp = dfr.next();
            //System.out.println(emp);
        }
        System.out.println(System.currentTimeMillis() - start);
        dfr.close();
    }



//============hadoop====================================================================================================
    @Test
    public void testHadoopSerial() throws Exception {

        long start = System.currentTimeMillis();
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("E:/avro/emp2.hadoop"));
        EmpWritable ew = new EmpWritable();
        for (int i = 1; i <= 1000000; i++) {
            Emp emp = new Emp();
            emp.setId(i);
            emp.setName("tom" + i);
            emp.setAge(i % 100);
            emp.setSalary(20000);
            emp.setAddress("昌平");
            ew.setEmp(emp);
            ew.write(dos);
        }
        System.out.println(System.currentTimeMillis() - start);
        dos.close();
    }

    @Test
    public void testHadoopDeSerial() throws Exception {

        DataInputStream dis = new DataInputStream(new FileInputStream("E:/avro/emp2.hadoop"));

        EmpWritable ew = new EmpWritable();
        long start = System.currentTimeMillis();
        for (int i = 1; i < 1000000; i++) {
            ew.readFields(dis);
            Emp emp = ew.getEmp();
        }
        System.out.println(System.currentTimeMillis() - start);
        dis.close();


    }

}
