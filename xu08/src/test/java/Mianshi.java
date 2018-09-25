import java.util.Stack;

public class Mianshi {
    public void mianShi(Stack<Integer> s1,Stack<Integer> s2) {
        //s2栈最后一位最小
        int c;
        //s1不为空
        while(!s1.empty()){
            Integer peek = s1.peek();
            if(peek>s2.peek()){
                c=s2.pop();
                s2.push(peek);
            }
        }

    }
}
