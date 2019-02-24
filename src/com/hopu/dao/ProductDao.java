package com.hopu.dao;

import com.hopu.vo.PageBean;
import com.hopu.vo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findHot() throws Exception;

    List<Product> findNew() throws Exception;

    Product getById(String pid) throws Exception;

    List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception;

    int getTotalRecord(String cid) throws Exception;

    List<Product> findAll() throws Exception;
}
