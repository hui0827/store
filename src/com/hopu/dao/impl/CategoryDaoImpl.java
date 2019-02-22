package com.hopu.dao.impl;

import com.hopu.dao.CategoryDao;
import com.hopu.vo.Category;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() throws Exception {
        QueryRunner qr = new QueryRunner(new ComboPooledDataSource());
        String sql = "select * from category";
        return qr.query(sql, new BeanListHandler<>(Category.class));
    }
}
