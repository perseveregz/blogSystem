package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.categoryMapper;
import com.gz.myblog.pojo.BlogCategory;
import com.gz.myblog.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-10:28
 * @VERSON:1.8
 */

@Service
public class categoryServiceImp implements categoryService {

    @Autowired
    private categoryMapper categoryMapper;

    /**
     * 获取所有的分类数量
     * @return
     */
    @Override
    public int getTotalCategories() {
        return categoryMapper.getTotalCategories(null);
    }

    /**
     * 获取所有的分类信息
     * @return
     */
    @Override
    public List<BlogCategory> getAllCategories() {
        return categoryMapper.findCategoryList(null);

    }
}
