package avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.*;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;
import tutorialspoint.Emp;

import java.beans.Encoder;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class TestAvro {
    public static void main(String[] args)  throws Exception{

        Emp emp = new Emp();

        emp.setId(1);
        emp.setName("tom");
        emp.setAge(20);
        emp.setSalary(20000);
        emp.setAddress("昌平");

        //
        File file = new File("E:/avro/emp.avro");
        //
        DatumWriter<Emp> dw = new SpecificDatumWriter<Emp>(Emp.class);

        //入口点，实例化DataFileWriter
        DataFileWriter<Emp> dfw = new DataFileWriter<Emp>(dw);

        //开始序列化
        //参数2：输出文件路径
        dfw.create(emp.getSchema(),file);

        //添加对象
        dfw.append(emp);

        dfw.close();
        System.out.println("data successfully serialized");


    }


    @Test
    public  void testDese()  throws Exception{

        DatumReader<Emp> dr = new SpecificDatumReader<Emp>();
        DataFileReader<Emp> dfr = new DataFileReader<Emp>(new File("E:/avro/emp.avro"),dr);

        while(dfr.hasNext()){
            Emp emp = dfr.next();
            System.out.println(emp);
        }
        dfr.close();
    }




    //不通过emp对象直接对schema操作
    @Test
    public void testSerial2() throws Exception{
        //通过schema文件生成schema对象
        Schema schema = new Schema.Parser().parse(new File("E:/avro/Emp.avsc"));
        //Instantiating the GenericRecord class.
        GenericRecord e1 = new GenericData.Record(schema);

        e1.put("name", "ramu");
        e1.put("id", 001);
        e1.put("salary",30000);
        e1.put("age", 25);
        e1.put("address", "chenni");

        DatumWriter dw = new SpecificDatumWriter();
        DataFileWriter<GenericRecord> dfw = new DataFileWriter<GenericRecord>(dw);

        dfw.create(schema,new File("D:/avro/emp.avro2"));

        dfw.append(e1);

        dfw.close();
    }

    //不通过emp对象直接对schema操作
    @Test
    public void testDeSerial2() throws Exception{
        //通过schema文件生成schema对象
        Schema schema = new Schema.Parser().parse(new File("D:/avro/Emp.avsc"));
        //Instantiating the GenericRecord class.
//        GenericRecord e1 = new GenericData.Record(schema);
//
//        e1.put("name", "ramu");
//        e1.put("id", 001);
//        e1.put("salary",30000);
//        e1.put("age", 25);
//        e1.put("address", "chenni");

        DatumReader dr = new SpecificDatumReader();
        DataFileReader<GenericRecord> dfr = new DataFileReader<GenericRecord>(new File("D:/avro/emp.avro2"),dr);

        while(dfr.hasNext()){
            GenericRecord record = dfr.next();
            System.out.println(record.get("name"));
        }
        dfr.close();

    }



    //=========================内存中的序列化与反序列化==========版本不对么？？？=====================================

    ByteArrayOutputStream baos ;
    @Test
    public void testSerial3() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(getClass().getResourceAsStream("Emp.avsc"));
        GenericData.Record record = new GenericData.Record(schema);
         record.put("name","ramu");
         record.put("id",1);
         record.put("salary",30000);
         record.put("age", 25);
         record.put("address", "chenni");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        DatumWriter<GenericRecord> writer = new GenericDatumWriter<GenericRecord>(schema);
        BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(baos, null);
        writer.write(record,encoder);
        encoder.flush();
        baos.close();
    }


    public void testDeserial3() throws IOException {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse(getClass().getResourceAsStream("E:/avro/fuxi/Emp.avsc"));
        GenericDatumReader<GenericData> reader = new GenericDatumReader<GenericData>(schema);
        Decoder decoder = DecoderFactory.get().binaryDecoder(baos.toByteArray(),null);
        GenericData result = reader.read(null, decoder);
        System.out.print(result.get());

    }

}
