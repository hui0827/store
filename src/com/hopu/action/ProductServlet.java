package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.service.ProductService;
import com.hopu.service.impl.ProductServiceImpl;
import com.hopu.vo.PageBean;
import com.hopu.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends BaseServlet {

    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pid = request.getParameter("pid");
            ProductService ps = new ProductServiceImpl();
            Product pro = ps.getById(pid);
            request.setAttribute("bean", pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/product_info.jsp";
    }

    public String findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int pageNumber = 1;
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = 12;
            String cid = request.getParameter("cid");
            ProductService ps = new ProductServiceImpl();
            PageBean<Product> pb = ps.findByPage(pageNumber, pageSize, cid);
            request.setAttribute("pb",pb);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","分页失败");
            return "/jsp/msg.jsp";
        }
        return "/jsp/product_list.jsp";
    }
}
