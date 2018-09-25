import com.study.mybaits.domian.Item;
import com.study.mybaits.domian.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class testItems {
    @Test
    public void testInsert() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        Orders or1 = new Orders();
        or1.setId(5);

        Item item = new Item();
//        item.setId();
        item.setIname("pea");
        item.setOrder(or1);
        sess.insert("items.insert",item);
        sess.commit();
        sess.close();
        in.close();
    }
    @Test
    public void testSelect() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        Item i1 = new Item();
        i1.setId(3);
        sess.selectOne("items.selectOne",i1);
        sess.commit();
        sess.close();
        in.close();

    }
}
