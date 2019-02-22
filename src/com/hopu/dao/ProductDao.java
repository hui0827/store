package com.hopu.dao;

import com.hopu.vo.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findHot() throws Exception;

    List<Product> findNew() throws Exception;
}
