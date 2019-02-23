package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.service.ProductService;
import com.hopu.service.impl.ProductServiceImpl;
import com.hopu.vo.Cart;
import com.hopu.vo.CartItem;
import com.hopu.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    public String add2cart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pid = request.getParameter("pid");
            int count = Integer.parseInt(request.getParameter("count"));
            ProductService ps = new ProductServiceImpl();
            Product product = ps.getById(pid);
            CartItem cartItem = new CartItem(product, count);
            Cart cart = getCart(request);
            cart.add2cart(cartItem);

            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg","加入购物车失败");
            return "/jsp/msg.jsp";
        }
        return null;
    }

    public String remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        Cart cart = getCart(request);
        cart.removeFromCart(pid);
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }

    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = getCart(request);
        cart.clearCart();
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
        return null;
    }
    /**
     * 获取购物车
     *
     * @return
     */
    private Cart getCart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        return cart;
    }
}
