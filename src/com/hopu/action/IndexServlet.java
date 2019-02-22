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

@WebServlet(name = "IndexServlet", urlPatterns = "/index")
public class IndexServlet extends BaseServlet {

    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductService ps = new ProductServiceImpl();
            List<Product> hotList = ps.findHot();
            List<Product> newList = ps.findNew();
            request.setAttribute("hList",hotList);
            request.setAttribute("nList",newList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/index.jsp";
    }
}
