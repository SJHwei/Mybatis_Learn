package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @author ShiWei
 * @date 2021/3/14 - 19:30
 *
 * 在mybatis中针对CRUD一共有四个注解：@select，@insert，@update，@delete
 */
@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("select * from user")
    @Results(id = "userMap",value={
            @Result(id = true,column = "id",property = "userId"),
            @Result(column = "username",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts",column = "id",one = @One(select = "com.itheima.dao.IAccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap(value={"userMap"})
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}") //第一种写法：SQL语句中不加% 【使用参数占位符】
    @ResultMap(value={"userMap"})
    List<User> findUserByName(String username);

}
