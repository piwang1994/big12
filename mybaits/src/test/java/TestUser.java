import com.study.mybaits.domian.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestUser {
    @Test
public void oneTomany() throws IOException {
    InputStream is = Resources.getResourceAsStream("mybaits-config.xml");
    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
    SqlSession session = factory.openSession();
    User user = new User();
    user.setId(3);
    User u = session.selectOne("users.selectByUid",user);

        System.out.println();
}
}
