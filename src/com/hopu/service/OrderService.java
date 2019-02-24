package com.hopu.service;

import com.hopu.vo.Order;
import com.hopu.vo.PageBean;

public interface OrderService {
    void save(Order order) throws Exception;

    PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws  Exception;

    Order getById(String oid) throws Exception;

    void update(Order order) throws Exception;
}
