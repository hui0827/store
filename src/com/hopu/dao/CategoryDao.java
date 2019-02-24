package com.hopu.dao;

import com.hopu.vo.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> findAll() throws Exception;

    void save(Category category) throws Exception;
}
