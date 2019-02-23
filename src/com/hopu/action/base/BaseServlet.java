package com.hopu.action.base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取方法名
        String methodName = request.getParameter("method");
        // 当没用指定要调用的方法时，那么默认请求的是execute()方法。
        if (methodName == null || methodName.isEmpty()) {
            methodName = "index";
        }
        try {
            // 通过方法名称获取方法的反射对象
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 反射方法目标方法，也就是说，如果methodName为add，那么就调用add方法。
            String path = (String) method.invoke(this, request, response);
            // 通过返回值完成请求转发
            if (path != null){
                request.getRequestDispatcher(path).forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 运行时异常500
            throw new RuntimeException();
        }
    }

    public String index (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("不要捣乱哦！");
        return null;
    }
}
