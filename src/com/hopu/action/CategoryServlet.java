package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.service.CategoryService;
import com.hopu.service.impl.CategoryServiceImpl;
import com.hopu.vo.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=utf-8");
            CategoryService cs = new CategoryServiceImpl();
            String json = cs.findAll();
//            System.out.println(json);
            response.getWriter().println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
