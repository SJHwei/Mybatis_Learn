package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
        //3. 使用工厂创建dao对象
        IUserDao userDao = new UserDaoImpl(factory);
        //4. 使用代理对象执行方法
        List<User> users = userDao.findAll(); //调用方法实现功能
        for(User user : users) {
            System.out.println(user);
        }
        //5. 释放资源
        in.close();
    }
}
