package com.itheima.dao;

import com.itheima.domain.Account;

import java.util.List;

/**
 * @author ShiWei
 * @date 2021/3/8 - 22:28
 */
public interface IAccountDao {

    /**
     * 查询所有账户，同时还要获取到当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询账户信息
     * @param id
     * @return
     */
    List<Account> findAccountByUid(Integer id);
}
