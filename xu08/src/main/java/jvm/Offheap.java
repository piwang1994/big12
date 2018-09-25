package jvm;

import java.nio.ByteBuffer;
import java.util.Stack;

public class Offheap {
    public static void main(String[] args){
        ByteBuffer buf = ByteBuffer.allocateDirect(5*1024* 1024 * 1024);
        System.out.println();
        buf=null;
        System.out.println();


        Stack stack = new Stack();
        stack.peek();

    }
}
