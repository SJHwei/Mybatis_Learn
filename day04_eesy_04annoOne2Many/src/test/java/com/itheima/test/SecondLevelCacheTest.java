package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author ShiWei
 * @date 2021/3/14 - 22:52
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws Exception {
        in.close();
    }

    @Test
    public void testFindOne() {

        User user = userDao.findById(61);
        System.out.println(user);
        session.close(); //释放一级缓存

        SqlSession session1 = factory.openSession(); //再次打开session
        IUserDao userDao1 = session1.getMapper(IUserDao.class);

        User user1 = userDao1.findById(61);
        System.out.println(user1);

        session1.commit();
        session1.close();

        //测试一级缓存，不用过多考虑一级缓存，一级缓存本来就有。
        System.out.println(user == user1);
    }
}
