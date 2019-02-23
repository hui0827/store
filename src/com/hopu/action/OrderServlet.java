package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.constant.Constant;
import com.hopu.service.OrderService;
import com.hopu.service.impl.OrderServiceImpl;
import com.hopu.utils.UUIDUtils;
import com.hopu.vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends BaseServlet {

    public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("msg", "请先去登录");
                return "/jsp/msg.jsp";
            }
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = 3;
            OrderService os = new OrderServiceImpl();

            PageBean<Order> pb = os.findMyOrdersByPage(pageNumber, pageSize, user.getUid());
            request.setAttribute("pb",pb);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/order_list.jsp";
    }

    public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                request.setAttribute("msg", "请先去登录");
                return "/jsp/msg.jsp";
            }
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            Order order = new Order();
            order.setOid(UUIDUtils.getId());
            order.setOrdertime(new Date());
            order.setState(Constant.ORDER_WEIFUKUAN);
            order.setTotal(cart.getTotal());

            order.setUser(user);
            for (CartItem ci : cart.getCartItems()) {
                OrderItem oi = new OrderItem();
                oi.setItemid(UUIDUtils.getId());
                oi.setCount(ci.getCount());
                oi.setProduct(ci.getProduct());
                oi.setSubtotal(ci.getSubtotal());
                oi.setOrder(order);
                order.getItems().add(oi);
            }
            OrderService os = new OrderServiceImpl();
            os.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/order_info.jsp";
    }
}
