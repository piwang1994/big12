public class Homework {


}
//    1.实现二叉树的遍历
//            前序遍历
//    中序遍历
//            后序遍历
//    层序遍历
//	2.设计一个类Person含有height、weight、age和blood是整数属性，
//    实现hashcode方法，将四个属性编排到一个整数中作为hashcode和equals方法
//		----------------------------
//    age|heght|weight|blood
//		----------------------------
class Person{
    public int height;//0000 000
    public int weight;//0000 0000 00 2**8=256KG
    public int age;    //0000 0000  2*7=128
    public int boold;//00 A 01B 10 AB 11o
    //0000 0000  | 0000 0000 | 0000 0000  0000 0000



    public Person(int height,int weight,int age ,int blood){
        this.height=height;
        this.weight=weight;
        this.age=age;
        this.boold=blood;
    }

    @Override
    public int hashCode() {
        return(int)((((this.weight <<2 ) | this.boold)<<20)|height<<8|age);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}

//
//    3.实现n阶方阵的平方算法，计算时间复杂度。


//    4.蜜蜂和熊问题
//		100只蜜蜂，2头熊，每只蜜蜂每次生产的蜂蜜量是1 ，有一个罐子，容量是50，
//    罐子的蜂蜜量一旦到达20，熊就一次性吃光。
//
//
//            5.和尚吃馒头问题
//		30和尚，100个馒头，每个和尚最多吃4个馒头，一次只能吃一个馒头 ，最少吃一个馒头。满足上述条件，尽快吃光
//    馒头。




