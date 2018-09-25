package zmytest;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestAvro {
    @Test
    public void testSerial() throws IOException {

        Emp emp = new Emp();

        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);
        emp.setSalary(20000);
        emp.setAddress("昌平");

        File file = new File("E:\\avro\\fuxi\\emp.avro");
        DatumWriter dr = new SpecificDatumWriter(Emp.class);
        DataFileWriter<Emp> dfw = new DataFileWriter<Emp>(dr);
        dfw.create(emp.getSchema(),file);
        dfw.append(emp);

        dfw.close();
    }
    @Test
    public void testDeserial() throws IOException {
        File file = new File("E:\\avro\\fuxi\\emp.avro");
        SpecificDatumReader<Emp> dw = new SpecificDatumReader<Emp>();
        DataFileReader<Emp> emps = new DataFileReader<Emp>(file, dw);

        for (Emp emp : emps) {
            System.out.print(emp);

        }

    }


}
