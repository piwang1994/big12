import com.study.mybaits.domian.Orders;
import com.study.mybaits.domian.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


public class TestOrder {
    @Test
    public void testInsert() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybaits-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();
        Orders order = new Orders();

        User user = new User();

        user.setId(4);
//        order.setId(1);
        order.setOrderno(7);
        order.setPrice(107);
        order.setUser(user);
        session.insert("orders.insert",order);
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void testSelectOne() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybaits-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();


        Object o = session.selectOne("orders.selectOne",4 );
        System.out.println(o);
        session.commit();
        session.close();
        is.close();
    }

    @Test
    public void testSelectOne2() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybaits-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = factory.openSession();


        Object o = session.selectOne("orders.selectOne2",4 );
        System.out.println(o);
        session.commit();
        session.close();
        is.close();
    }
}
