package com.hopu.dao;

import com.hopu.vo.Order;
import com.hopu.vo.OrderItem;
import com.hopu.vo.PageBean;

import java.util.List;

public interface OrderDao {
    void save(Order order) throws Exception;

    void saveItem(OrderItem oi) throws Exception;

    int getTotalRecord(String uid) throws Exception;

    List<Order> findMyOrderByPage(PageBean<Order> pb, String uid) throws Exception;

    Order getById(String oid) throws Exception;

    void update(Order order) throws Exception;
}
