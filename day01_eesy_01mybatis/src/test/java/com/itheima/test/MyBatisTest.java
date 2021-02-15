package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * mybatis的入门案例
 */
public class MyBatisTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1. 读取配置文件, 生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml"); //这里边有连接数据库的信息
        //2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder(); //有了工厂就可以生产操作对象
        SqlSessionFactory factory = builder.build(in);
        //3. 使用工厂生产SqlSession对象
        sqlSession = factory.openSession(); //这个对象就可以操作数据库, 但是不是用它操作, 而是用dao操作, 所以有了代理对象
        //4. 使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class); //由于我们只有dao接口, 所以需要创建一个代理对象, 有了代理对象就说明我们方法已经增强过了, 能实现功能了
    }

    @After //用于在测试方法执行之后执行
    public void destroy() throws Exception {
        //提交事务
        sqlSession.commit();
        //6. 释放资源
        sqlSession.close(); //最后释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {

        //5. 执行查询所有方法
        List<User> users = userDao.findAll(); //调用方法实现功能
        for(User user : users) {
            System.out.println(user);
        }

    }

    /**
     * 测试保存操作
     */
    @Test
    public void testSave() {
        User user = new User();
        user.setUserName("modify User property");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        System.out.println("保存操作之前: " + user);

        //5. 执行保存方法
        userDao.saveUser(user);

        System.out.println("保存操作之后: " + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserId(50);
        user.setUserName("mybatis Update user");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        //5. 执行更新方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete() {

        //5. 执行删除方法
        userDao.deleteUser(48);
    }

    /**
     * 测试查询一个操作
     */
    @Test
    public void testFindOne() {

        //5. 执行查询一个方法
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试模糊查询操作
     */
    @Test
    public void testFindByName() {

        //5. 执行模糊查询方法
//        List<User> users = userDao.findByName("%王%");
        List<User> users = userDao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试总记录条数操作
     */
    @Test
    public void testFindTotal() {

        //5. 执行查询总条数方法
        int count = userDao.findTotal();
        System.out.println(count);
    }

    /**
     * 测试使用QueryVo作为查询条件
     */
    @Test
    public void testFindByVo() {

        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        //5. 执行模糊查询方法
        List<User> users = userDao.findUserByVo(vo);
        for (User u : users) {
            System.out.println(user);
        }
    }
}
