package com.hopu.service.impl;

import com.hopu.dao.OrderDao;
import com.hopu.dao.impl.OrderDaoImpl;
import com.hopu.service.OrderService;
import com.hopu.vo.Order;
import com.hopu.vo.OrderItem;
import com.hopu.vo.PageBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void save(Order order) throws Exception {
        OrderDao od = new OrderDaoImpl();
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        Connection conn = null;
        try {
            conn = cpds.getConnection();
            conn.setAutoCommit(false);
            od.save(order);
            for (OrderItem oi:order.getItems()){
                od.saveItem(oi);
            }
            conn.commit();
            conn.close();
        } catch (Exception e) {
            conn.rollback();
            conn.close();
            e.printStackTrace();
        }
    }

    @Override
    public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
        OrderDao od = new OrderDaoImpl();
        PageBean<Order> pb = new PageBean<>(pageNumber, pageSize);
        int total = od.getTotalRecord(uid);
        pb.setTotalRecord(total);
        List<Order> data = od.findMyOrderByPage(pb, uid);
        pb.setData(data);
        return pb;
    }

    @Override
    public Order getById(String oid) throws Exception {
        OrderDao od = new OrderDaoImpl();
        return od.getById(oid);
    }

    @Override
    public void update(Order order) throws Exception {
        OrderDao od = new OrderDaoImpl();
        od.update(order);
    }
}
