package com.hopu.service;

import com.hopu.vo.Category;

import java.util.List;

public interface CategoryService {
    String findAll() throws Exception;

    List<Category> findList() throws Exception;

    void save(Category category) throws Exception;
}
