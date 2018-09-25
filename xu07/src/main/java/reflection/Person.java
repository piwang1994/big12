package reflection;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private int age;
    private int id;
    private String sex;

    public Person(){
        System.out.println("调用了Person的空参构造");
    }


    private Person(String name){
        this.name=name;
        System.out.println("这是person中name的构造方法");
    }

    private Person(Integer[][] n){
        System.out.println("这是person中Integer[][]的构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
