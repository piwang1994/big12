package calllog;

public class GenKeyUtil {


    //生成主叫key
    public static String genCaller(String caller, String callee, String time) {

        //1,13843838438,201808160830,1,13800000000

        String key = genKey(caller, time);
        String rowKey = key + "," + caller + "," + time + "," + "1" + "," + callee;

        return rowKey;


    }


    //生成被叫key
    public static String genCallee(String callee, String caller, String time) {
        //3,13800000000,201808160830,0,13843838438
        String key = genKey(callee, time);

        String rowKey = key + "," + callee + "," + time + "," + "0" + "," + caller;

        return rowKey;

    }


    public static String genKey(String call, String time) {
        int i = Integer.parseInt(call.substring(7));
        int j = Integer.parseInt(time.substring(0, 6));

        return (i + j) % 10 + "";

    }

    public static void main(String[] args) {
        System.out.println(genCallee("13800000000", "13843838438", "20180816083002"));
    }


}
