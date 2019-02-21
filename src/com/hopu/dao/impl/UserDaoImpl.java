package com.hopu.dao.impl;

import com.hopu.dao.UserDao;
import com.hopu.vo.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    @Override
    public void register(User user) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "insert into user values (?,?,?,?,?,?,?,?,?,?)";
        qr.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
                user.getEmail(), user.getTelephone(), user.getBirthday(), user.getSex(),
                user.getState(), user.getCode());
    }

    @Override
    public User getByCode(String code) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from user where code = ? limit 1";
        return qr.query(sql,new BeanHandler<>(User.class), code);
    }

    @Override
    public void update(User user) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "update user set password = ?,sex = ?,state = ?,code = ? where uid = ?";
        qr.update(sql, user.getPassword(), user.getSex(), user.getState(), user.getCode(), user.getUid());
    }

    @Override
    public User login(String username, String password) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from user where username = ? and password = ?";
        return qr.query(sql, new BeanHandler<>(User.class), username, password);
    }
}
