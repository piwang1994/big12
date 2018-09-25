import org.apache.hadoop.io.Text;

public class TestHashCode {


    public static void main(String[] args) {

        Text text = new Text("300");
        Text text2 = new Text("are");
        Text text3 = new Text("tom");
        Text text4 = new Text("world");



        Text text5 = new Text("200");
        Text text6 = new Text("ok");
        Text text7 = new Text("tomas");

        System.out.println(text.hashCode() );
        System.out.println(text2.hashCode());
        System.out.println(text3.hashCode());
        System.out.println(text4.hashCode());

        System.out.println(text5.hashCode());
        System.out.println(text6.hashCode());
        System.out.println(text7.hashCode());

        System.out.println((-8 ) % 3);
    }


}
