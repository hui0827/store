package com.hopu.service.impl;

import com.hopu.constant.Constant;
import com.hopu.dao.UserDao;
import com.hopu.dao.impl.UserDaoImpl;
import com.hopu.service.UserService;
import com.hopu.utils.MailUtils;
import com.hopu.vo.User;

import javax.mail.MessagingException;

public class UserServiceImpl implements UserService {
    @Override
    public void register(User user) throws Exception {
        UserDao ud = new UserDaoImpl();
        ud.register(user);
        MailUtils.sendMail(user.getEmail(),user.getCode());
    }

    @Override
    public User active(String code) throws Exception {
        UserDao ud = new UserDaoImpl();
        User user = ud.getByCode(code);
        if (user == null){
            return null;
        }
        user.setState(Constant.USER_IS_ACTIVE);
        user.setCode(null);
        ud.update(user);
        return user;
    }

    @Override
    public User login(String username, String password) throws Exception {
        UserDao ud = new UserDaoImpl();
        return ud.login(username,password);
    }
}
