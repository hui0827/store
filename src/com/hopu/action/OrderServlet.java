package com.hopu.action;

import com.hopu.action.base.BaseServlet;
import com.hopu.constant.Constant;
import com.hopu.service.OrderService;
import com.hopu.service.impl.OrderServiceImpl;
import com.hopu.utils.PaymentUtil;
import com.hopu.utils.UUIDUtils;
import com.hopu.vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends BaseServlet {

    public String callback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受第三方数据
        try {
            String p1_MerId = request.getParameter("p1_MerId");
            String r0_Cmd = request.getParameter("r0_Cmd");
            String r1_Code = request.getParameter("r1_Code");
            String r2_TrxId = request.getParameter("r2_TrxId");
            String r3_Amt = request.getParameter("r3_Amt");
            String r4_Cur = request.getParameter("r4_Cur");
            String r5_Pid = request.getParameter("r5_Pid");
            String r6_Order = request.getParameter("r6_Order");
            String r7_Uid = request.getParameter("r7_Uid");
            String r8_MP = request.getParameter("r8_MP");
            String r9_BType = request.getParameter("r9_BType");
            String rb_BankId = request.getParameter("rb_BankId");
            String ro_BankOrderId = request.getParameter("ro_BankOrderId");
            String rp_PayDate = request.getParameter("rp_PayDate");
            String rq_CardNo = request.getParameter("rq_CardNo");
            String ru_Trxtime = request.getParameter("ru_Trxtime");
            // 身份校验 --- 判断是不是支付公司通知你
            String hmac = request.getParameter("hmac");
            String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
                    "keyValue");

            // 自己对上面数据进行加密 --- 比较支付公司发过来hamc
            boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
                    r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
                    r8_MP, r9_BType, keyValue);
            if (isValid) {
                // 响应数据有效
                if (r9_BType.equals("1")) {
                    // 浏览器重定向
                    System.out.println("111");
                    request.setAttribute("msg", "您的订单号为:" + r6_Order + ",金额为:" + r3_Amt + "已经支付成功,等待发货~~");

                } else if (r9_BType.equals("2")) {
                    // 服务器点对点 --- 支付公司通知你
                    System.out.println("付款成功！222");
                    // 修改订单状态 为已付款
                    // 回复支付公司
                    response.getWriter().print("success");
                }

                //修改订单状态
                OrderService s = new OrderServiceImpl();
                Order order = s.getById(r6_Order);
                order.setState(Constant.ORDER_YIFUKUAN);

                s.update(order);

            } else {
                // 数据无效
                System.out.println("数据被篡改！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "支付失败");
        }


        return "/jsp/msg.jsp";
    }

    public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //接受参数
            String address = request.getParameter("address");
            String name = request.getParameter("name");
            String telephone = request.getParameter("telephone");
            String oid = request.getParameter("oid");


            //通过id获取order
            OrderService s = new OrderServiceImpl();
            Order order = s.getById(oid);

            order.setAddress(address);
            order.setName(name);
            order.setTelephone(telephone);

            //更新order
            s.update(order);


            // 组织发送支付公司需要哪些数据
            String pd_FrpId = request.getParameter("pd_FrpId");
            String p0_Cmd = "Buy";
            String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
            String p2_Order = oid;
            String p3_Amt = "0.01";
            String p4_Cur = "CNY";
            String p5_Pid = "";
            String p6_Pcat = "";
            String p7_Pdesc = "";
            // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
            // 第三方支付可以访问网址
            String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
            String p9_SAF = "";
            String pa_MP = "";
            String pr_NeedResponse = "1";
            // 加密hmac 需要密钥
            String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
            String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
                    p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
                    pd_FrpId, pr_NeedResponse, keyValue);


            //发送给第三方
            StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
            sb.append("p0_Cmd=").append(p0_Cmd).append("&");
            sb.append("p1_MerId=").append(p1_MerId).append("&");
            sb.append("p2_Order=").append(p2_Order).append("&");
            sb.append("p3_Amt=").append(p3_Amt).append("&");
            sb.append("p4_Cur=").append(p4_Cur).append("&");
            sb.append("p5_Pid=").append(p5_Pid).append("&");
            sb.append("p6_Pcat=").append(p6_Pcat).append("&");
            sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
            sb.append("p8_Url=").append(p8_Url).append("&");
            sb.append("p9_SAF=").append(p9_SAF).append("&");
            sb.append("pa_MP=").append(pa_MP).append("&");
            sb.append("pd_FrpId=").append(pd_FrpId).append("&");
            sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
            sb.append("hmac=").append(hmac);

            response.sendRedirect(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "支付失败");
            return "/jsp/msg.jsp";
        }
        return null;
    }

    public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String oid = request.getParameter("oid");
            OrderService os = new OrderServiceImpl();
            Order order = os.getById(oid);
            request.setAttribute("bean", order);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "订单查询失败");
            return "/jsp/msg.jsp";
        }
        return "/jsp/order_info.jsp";
    }

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
            request.setAttribute("pb", pb);

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
            cart.clearCart();
            request.setAttribute("bean",order);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/jsp/order_info.jsp";
    }
}
