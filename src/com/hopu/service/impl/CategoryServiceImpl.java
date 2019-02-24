package com.hopu.service.impl;

import com.google.gson.Gson;
import com.hopu.dao.CategoryDao;
import com.hopu.dao.impl.CategoryDaoImpl;
import com.hopu.service.CategoryService;
import com.hopu.vo.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public String findAll() throws Exception {
//        CategoryDao cd = new CategoryDaoImpl();
//        List<Category> list = cd.findAll();
        List<Category> list = findList();
        if (list != null && !list.isEmpty()) {
            Gson gson = new Gson();
            return gson.toJson(list);
        }
        return null;
    }

    @Override
    public List<Category> findList() throws Exception {
        CategoryDao cd = new CategoryDaoImpl();
        return cd.findAll();
    }

    @Override
    public void save(Category category) throws Exception {
        CategoryDao cd = new CategoryDaoImpl();
        cd.save(category);
    }
}
