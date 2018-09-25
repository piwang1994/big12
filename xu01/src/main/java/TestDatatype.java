import org.junit.Test;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;






public class TestDatatype {

    public static String testUnicodeString(String str){

        StringBuilder builder = new StringBuilder();
        char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        for(int i=0;i<str.length();i++){
            int ii = str.charAt(i);
            System.out.println(ii);
            builder.append("\\u");
            builder.append(chars[ii >> 12 & 0xf]);
            builder.append(chars[ii >> 8 & 0xf]);
            builder.append(chars[ii >> 4 & 0xf]);
            builder.append(chars[ii >> 0 & 0xf]);
        }
        return builder.toString();
    }

//    public static void main(String[] args){
//        String a = testUnicodeString(" 中");
//        System.out.println(a);
//    }




    @Test
    public void allOut() {
        int clos = 0;
        for (int i = 0; i < 0xffff; i++) {
            System.out.printf("%d : %s\t", i, (char) i);
            clos++;


            if (clos >10) {
                clos=0;
                System.out.println();
            }

        }
    }


        @Test
    public void testMd5() throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("md5");
        byte[] digest = md.digest("abc".getBytes());  //16个字节

        StringBuilder builder = new StringBuilder();
        System.out.println(digest.length);
        char[] chars={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        for (byte b : digest) {
        builder.append(chars[b >> 4 & 0xf]);
        builder.append(chars[b >> 0 & 0xf]);
        }

        System.out.println(builder.toString());
        System.out.println(builder.toString().length());
    }


    public void test1() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\Program Files\\feiq\\Recv Files\\8888.txt"));
        char[] chars = new char[2];
        int read;
        while((read = isr.read(chars))!=-1){

        };
    }

//    把long数据转换成字节数组

    public static Byte[] testLong(long l){

//        byte b1 = (byte) (l >> 56 & 0xff);
//        byte b2 = (byte) (l >> 48 & 0xff);
//        byte b3 = (byte) (l >> 40 & 0xff);
//        byte b4 = (byte) (l >> 32 & 0xff);
//        byte b5 = (byte) (l >> 24 & 0xff);
//        byte b6 = (byte) (l >> 16 & 0xff);
//        byte b7 = (byte) (l >> 8 & 0xff);
//        byte b8 = (byte) (l & 0xff);
//
//        return new Byte[]{b1,b2,b3,b4,b5,b6,b7,b8};
//
        Byte[] bytes = new Byte[8];
       bytes[0]=(byte) (l >> 56 & 0xff);
       bytes[1]=(byte) (l >> 48 & 0xff);
       bytes[2]=(byte) (l >> 40 & 0xff);
       bytes[3]=(byte) (l >> 32 & 0xff);
       bytes[4]=(byte) (l >> 24 & 0xff);
       bytes[5]=(byte) (l >> 16 & 0xff);
       bytes[6]=(byte) (l >> 8  & 0xff);
       bytes[7]=(byte) (l & 0xff);
       return bytes;

    }


//    public static void main(String[] args) throws IOException {
////        Byte[] bytes = testLong(1234567890987654321L);
////        for (Byte aByte : bytes) {
////            System.out.print(aByte+ " ");
//////            17 34 16 -12 -79 108 28 -79
//////            17 34 16 -12 -79 108 28 -79
//////            17 34 16 -12 -79 108 28 -79
////        }
//
//        Fileout();
//    }


//    把字节数组数据转换成long
    public static long bytes2long(Byte[] bytes){
            long l0 = (bytes[0]&0xff<< 56);
            long l1 = (bytes[1]&0xff << 48);
            long l2 = (bytes[2]&0xff << 40);
            long l3 = (bytes[3]&0xff << 32);
            long l4 = (bytes[4]&0xff << 24);
            long l5 = (bytes[5]&0xff << 16);
            long l6 = (bytes[6]&0xff << 8);
            long l7 = (bytes[7]&0xff);

        return l0 | l1 | l2 | l3 | l4 | l5 | l6 | l7;

    }


//    通过程序创建文本文件，内容是abc，采用uncode码，文件大小是10字节

    public static void Fileout() throws IOException {
        FileOutputStream fos = new FileOutputStream("E:/888.txt");

        fos.write("a".getBytes("unicode"));
        fos.write("b".getBytes("unicode"));
        fos.write("c".getBytes("unicode"));
        fos.close();
    }

//    将byte变换成无符号的整数(0 ~ 255 , 正数不变)
public static int nofuhao(byte b){
            int i = b & 0xff;
            return i;
}


//    有5亿整数(非负)，去重计算不同整数的个数，300M内存
    @Test
    public  void Five(int[] is){
//        int maxValue = Integer.MAX_VALUE;
//        System.out.println(maxValue);
        for (int i : is) {
            int 字节位 = i / 8;
             if(i%8==0){
                 
             }
        }

    }


}