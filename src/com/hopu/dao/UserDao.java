package com.hopu.dao;

import com.hopu.vo.User;

import java.sql.SQLException;

public interface UserDao {

    void register(User user) throws Exception;

    User getByCode(String code) throws Exception;

    void update(User user) throws Exception;

    User login(String username, String password) throws Exception;
}
