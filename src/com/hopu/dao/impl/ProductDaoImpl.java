package com.hopu.dao.impl;

import com.hopu.constant.Constant;
import com.hopu.dao.ProductDao;
import com.hopu.vo.PageBean;
import com.hopu.vo.Product;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findHot() throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from product where is_hot = ? and pflag = ? order by pdate desc limit ?";
        return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_HOT, Constant.PRODUCT_IS_NOT_DOWN, Constant.PRODUCT_NUM);
    }

    @Override
    public List<Product> findNew() throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from product where pflag = ? order by pdate desc limit ?";
        return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_NOT_DOWN, Constant.PRODUCT_NUM);
    }

    @Override
    public Product getById(String pid) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from product where pid = ?";
        return qr.query(sql, new BeanHandler<>(Product.class), pid);
    }

    @Override
    public List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from product where cid = ? and pflag = ? order by pdate desc limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Product.class), cid, Constant.PRODUCT_IS_NOT_DOWN, pb.getStartIndex(), pb.getPageSize());
    }

    @Override
    public int getTotalRecord(String cid) throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select count(*) from product where cid = ? and pflag = ?";
        return ((Long)qr.query(sql, new ScalarHandler<>(), cid, Constant.PRODUCT_IS_NOT_DOWN)).intValue();
    }

    @Override
    public List<Product> findAll() throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from product where pflag = ? order by pdate desc";
        return qr.query(sql, new BeanListHandler<>(Product.class), Constant.PRODUCT_IS_NOT_DOWN);
    }
}
