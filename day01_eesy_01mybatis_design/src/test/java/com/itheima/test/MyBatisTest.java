package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MyBatisTest {

    public static void main(String[] args) throws Exception {
        //1. 读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml"); //这里边有连接数据库的信息
        //2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); //有了工厂就可以生产操作对象
        SqlSessionFactory factory = builder.build(in);
        //3. 使用工厂生产SqlSession对象
        SqlSession session = factory.openSession(); //这个对象就可以操作数据库, 但是不是用它操作, 而是用dao操作, 所以有了代理对象
        //4. 使用SqlSession创建Dao接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class); //由于我们只有dao接口, 所以需要创建一个代理对象, 有了代理对象就说明我们方法已经增强过了, 能实现功能了
        //5. 使用代理对象执行方法
        List<User> users = userDao.findAll(); //调用方法实现功能
        for(User user : users) {
            System.out.println(user);
        }
        //6. 释放资源
        session.close(); //最后释放资源
        in.close();
    }
}
