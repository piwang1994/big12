import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class jsonDemo {
    public static void main(String[] args) throws IOException {
//        String line ="{\"person\":[{\"name\":\"tomas\",\"wife\":[\"marry\",\"jenny\"],\"age\":20},{\"name\":\"tom\",\"wife\":[\"marry\",\"jenny\"],\"age\":20}]}";
//        JSONObject j = JSON.parseObject(line);
//        JSONArray jsonArray = j.getJSONArray("person");
//
//        for (Object obj: jsonArray) {
//            JSONObject jsonObject = JSON.parseObject(obj.toString());
//            if(jsonObject.get("name").toString().equals("tomas")){
//                JSONArray wifes = jsonObject.getJSONArray("wife");
//                for (Object wife : wifes) {
//                    System.out.println(wife.toString());
//                }
//            }
//        }


        BufferedReader br = new BufferedReader(new FileReader("D:/js/temptags.txt"));
        String line;
        while((line=br.readLine())!=null){
            String log = line.split("\t")[1];
            JSONObject job = JSON.parseObject(log);
            if(job.get("extInfoList")!=null){
                Object extIfoList= job.get("extInfoList");
                JSONArray extIfoList1 = (JSONArray) extIfoList;
                for (Object o : extIfoList1) {
                    JSONObject ojs = JSON.parseObject(o.toString());
                    if(ojs.containsKey("values")&&(ojs.get("title").toString().equals("contentTags"))){
                        System.out.println(ojs.get("values"));


                }
            }}


        }


    }

}
