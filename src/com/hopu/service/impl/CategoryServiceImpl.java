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
        CategoryDao cd = new CategoryDaoImpl();
        List<Category> list = cd.findAll();
        if (list != null && !list.isEmpty()) {
            Gson gson = new Gson();
            return gson.toJson(list);
        }
        return null;
    }
}
