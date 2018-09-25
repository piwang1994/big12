package udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;


@Description(
        name = "myudf",
        value = "this is a sum function",
        extended = "eg: select myudf(1,2) => 3 , select myudf(\"hello\",\"world\") => helloworld"
)


public class MyUDF extends UDF {

    public int evaluate(int i , int j){
        return i+j;
    }

    public String evaluate(String a, String b){
        return a+b;
    }
}
