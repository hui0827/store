package com.hopu.dao.impl;

import com.hopu.constant.Constant;
import com.hopu.dao.ProductDao;
import com.hopu.vo.Product;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

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
}
