package com.hopu.dao.impl;

import com.hopu.dao.OrderDao;
import com.hopu.vo.Order;
import com.hopu.vo.OrderItem;
import com.hopu.vo.PageBean;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void save(Order order) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "insert into order values(?,?,?,?,?,?,?,?)";
        qr.update(sql, order.getOid(), order.getOrdertime(), order.getTotal(),
                order.getState(), order.getAddress(), order.getName(), order.getTelephone(), order.getUser().getUid());
    }

    @Override
    public void saveItem(OrderItem oi) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "insert into orderitem values(?,?,?,?,?)";
        qr.update(sql, oi.getItemid(), oi.getCount(), oi.getSubtotal(), oi.getProduct().getPid(), oi.getOrder().getOid());
    }

    @Override
    public int getTotalRecord(String uid) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select count(*) from orders where uid = ?";
        return ((Long) qr.query(sql, new ScalarHandler<>(), uid)).intValue();
    }

    @Override
    public List<Order> findMyOrderByPage(PageBean<Order> pb, String uid) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from orders where uid = ? order by ordertime desc limit ?,?";
        List<Order> list = qr.query(sql, new BeanListHandler<>(Order.class), uid, pb.getStartIndex(), pb.getPageSize());
        return list;
    }
}
