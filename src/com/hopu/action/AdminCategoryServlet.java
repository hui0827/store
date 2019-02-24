package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.service.CategoryService;
import com.hopu.service.impl.CategoryServiceImpl;
import com.hopu.vo.Category;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminCategoryServlet", urlPatterns = "/adminCategory")
public class AdminCategoryServlet extends BaseServlet {

    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> map = request.getParameterMap();
            Category category = new Category();
            BeanUtils.populate(category, map);
            CategoryService cs = new CategoryServiceImpl();
            cs.save(category);
            response.sendRedirect(request.getContextPath()+"/adminCategory/method=findAll");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return "/admin/category/add.jsp";
    }

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CategoryService cs = new CategoryServiceImpl();
            List<Category> list = cs.findList();
            request.setAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/category/list.jsp";
    }
}
