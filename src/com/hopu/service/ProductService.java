package com.hopu.service;

import com.hopu.vo.PageBean;
import com.hopu.vo.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHot() throws Exception;

    List<Product> findNew() throws Exception;

    Product getById(String pid) throws Exception;

    PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception;

    List<Product> findAll() throws Exception;
}
