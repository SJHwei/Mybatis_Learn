package com.itheima.dao;

import com.itheima.domain.Role;

import java.util.List;

/**
 * @author ShiWei
 * @date 2021/3/10 - 22:13
 */
public interface IRoleDao {

    /**
     * 查询所有角色
     * @return
     */
    List<Role> findAll();
}
