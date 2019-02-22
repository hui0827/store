package com.hopu.service.impl;

import com.hopu.dao.ProductDao;
import com.hopu.dao.impl.ProductDaoImpl;
import com.hopu.service.ProductService;
import com.hopu.vo.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> findHot() throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.findHot();
    }

    @Override
    public List<Product> findNew() throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.findNew();
    }
}
