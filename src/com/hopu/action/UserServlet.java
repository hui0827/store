package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.constant.Constant;
import com.hopu.service.UserService;
import com.hopu.service.impl.UserServiceImpl;
import com.hopu.utils.UUIDUtils;
import com.hopu.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 用户模块
 */
@WebServlet(name = "UserServlet", urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    // 用户注册
    public String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,map);
            user.setUid(UUIDUtils.getId());
            user.setState(Constant.USER_IS_NOT_ACTIVE);
            user.setCode(UUIDUtils.getCode());
            UserService us = new UserServiceImpl();
            us.register(user);
            request.setAttribute("msg","注册成功，请激活！");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","注册失败");
        }
        return "/jsp/msg.jsp";
    }

    public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String code = request.getParameter("code");
            UserService us = new UserServiceImpl();
            User user = us.active(code);
            if (user == null){      // 未找到用户
                request.setAttribute("msg","激活失败，请重新激活或重新注册");
            }
            request.setAttribute("msg","激活成功，可以登录了");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","激活失败，请重新激活或重新注册");
        }
        return "/jsp/msg.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserService us = new UserServiceImpl();
            User user = us.login(username, password);
            if (user == null) {
                request.setAttribute("msg","用户名或者密码不正确");
                return "/jsp/login.jsp";
            }
            if (Constant.USER_IS_NOT_ACTIVE == user.getState()){
                request.setAttribute("msg","账户未激活，请激活后登录");
                return "/jsp/msg.jsp";
            }
            request.getSession().setAttribute("user",user);
            response.sendRedirect(request.getContextPath());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","用户登录失败");
            return "/jsp/msg.jsp";
        }
        return null;
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return "/jsp/login.jsp";
    }

    public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return "/jsp/register.jsp";
    }

}
