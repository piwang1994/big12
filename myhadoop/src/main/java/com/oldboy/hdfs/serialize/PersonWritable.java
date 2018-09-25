package com.oldboy.hdfs.serialize;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PersonWritable implements Writable {


    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //串行化
    public void write(DataOutput out) throws IOException {
        //序列化name字段
        out.writeUTF(person.getName());
        //序列化age字段
        out.writeInt(person.getAge());

    }

    //反串行化
    public void readFields(DataInput in) throws IOException {
        person = new Person();

        //反序列化name字段
        person.setName(in.readUTF());
        //反序列化age字段
        person.setAge(in.readInt());

    }
}
