package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.service.ProductService;
import com.hopu.service.impl.ProductServiceImpl;
import com.hopu.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminProductServlet", urlPatterns = "/adminProduct")
public class AdminProductServlet extends BaseServlet {

    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductService ps = new ProductServiceImpl();
            List<Product> list = ps.findAll();
            request.setAttribute("list", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/admin/product/list.jsp";
    }
}
