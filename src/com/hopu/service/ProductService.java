package com.hopu.service;

import com.hopu.vo.Product;

import java.util.List;

public interface ProductService {
    List<Product> findHot() throws Exception;

    List<Product> findNew() throws Exception;
}
