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
 * @date 2021/3/8 - 22:31
 */
public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        in.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFirstLevelCache(){
        SqlSession sqlSession1 = factory.openSession();
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close(); //一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();

        System.out.println(user1 == user2);
    }

//    /**
//     * 测试缓存的同步
//     */
//    @Test
//    public void testClearCache(){
//        //1.根据id查询用户
//        User user1 = userDao.findById(41);
//        System.out.println(user1);
//        //2.更新用户信息
//        userDao = sqlSession.getMapper(IUserDao.class);
//        user1.setUsername("update user clear cache");
//        user1.setAddress("北京海淀区");
//        userDao.updateUser(user1);
//
//        //3.再次查询id为41的用户
//        User user2 = userDao.findById(41);
//        System.out.println(user2);
//
//        System.out.println(user1 == user2);
//    }
}
