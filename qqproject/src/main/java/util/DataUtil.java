package util;

import java.io.*;

public class DataUtil {
    public static int bytesToint(byte[] bytes){

        return (bytes[3]&0xff)<<24| (bytes[2]&0xff)<<16| (bytes[1]&0xff)<<8|bytes[0]&0xff;
    }

    public static byte[] intTobytes(int n){
        byte[] bytes4 = new byte[4];
        bytes4[3]=(byte)(n>> 24);
        bytes4[2]=(byte)(n>>16);
        bytes4[1]=(byte)(n>>8);
        bytes4[0]=(byte)(n);
        return bytes4;
    }

    /*
    串行化
     */
    public static byte[] serialData(Object obj) throws IOException {
        //串行化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        baos.close();
        return baos.toByteArray();
    }

    /*
    反串行化
     */
    public static Object deserialData(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object copy = ois.readObject();
        ois.close();
        bais.close();
        return copy;
    }



}
