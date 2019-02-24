package com.hopu.service.impl;

import com.hopu.dao.ProductDao;
import com.hopu.dao.impl.ProductDaoImpl;
import com.hopu.service.ProductService;
import com.hopu.vo.PageBean;
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

    @Override
    public Product getById(String pid) throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.getById(pid);
    }

    @Override
    public PageBean<Product> findByPage(int pageNumber, int pageSize, String cid) throws Exception {
        ProductDao pd = new ProductDaoImpl();
        PageBean<Product> pb = new PageBean<>(pageNumber, pageSize);
        List<Product> list = pd.findByPage(pb, cid);
        pb.setData(list);

        int total = pd.getTotalRecord(cid);
        pb.setTotalRecord(total);
        return pb;
    }

    @Override
    public List<Product> findAll() throws Exception {
        ProductDao pd = new ProductDaoImpl();
        return pd.findAll();
    }
}
