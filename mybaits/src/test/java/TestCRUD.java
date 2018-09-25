import com.study.mybaits.domian.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestCRUD {
    @Test
    public void testInsert() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        User user = new User();

        user.setName("tommm");
        user.setAge(10);

        sess.insert("users.insert",user);
        sess.commit();
        sess.close();
        System.out.println("ojbk");
    }

    @Test
    public void testUpd() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        User user = new User();
        user.setId(1);
        user.setName("tttt");
        user.setAge(10);

        sess.update("users.updateOne",user);
        sess.commit();
        sess.close();
        System.out.println("ojbk");
    }

    @Test
    public void testDel() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        User user = new User();
        user.setId(1);


        sess.update("users.deleteOneById",user);
        sess.commit();
        sess.close();
        System.out.println("ojbk");
    }

    @Test
    public void testSel() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();
        User user = new User();
        user.setId(2);


        User u = sess.selectOne("users.selectById", 3);
        sess.commit();
        sess.close();
        System.out.println(u.getId()+u.getAge()+u.getName());
    }
    @Test
    public void testAll() throws IOException {
        //加载设置文件
        InputStream in = Resources.getResourceAsStream("mybaits-config.xml");
        //创建会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(in);
        SqlSession sess = sf.openSession();

        User user = new User();
        user.setId(2);

        List<User> users = sess.selectList("users.selectAll");
        sess.commit();
        sess.close();
        System.out.println(users);
    }

}
