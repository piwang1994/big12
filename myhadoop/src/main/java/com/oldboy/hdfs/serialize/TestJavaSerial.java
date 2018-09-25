package com.oldboy.hdfs.serialize;

import org.junit.Test;

import java.io.*;

public class TestJavaSerial {


    /**
     * 测试java的序列化
     */
    @Test
    public void testSerial() throws Exception {
        Integer i = 100;

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/serial/serial.j"));
        oos.writeObject(i);

        oos.close();


    }

    @Test
    public void testDeserial() throws  Exception{

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:/serial/serial.j"));

        Object o = ois.readObject();

        System.out.println((Integer)o);


    }

    @Test
    public void testPersonSerial() throws Exception{

        Person p = new Person("tom",20);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:/serial/person.j"));

        oos.writeObject(p);

        oos.close();


    }

    /**
     * person反序列化
     */



}
