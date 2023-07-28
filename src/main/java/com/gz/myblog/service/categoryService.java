package com.gz.myblog.service;

import com.gz.myblog.pojo.BlogCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-10:28
 * @VERSON:1.8
 */

public interface categoryService {
    int getTotalCategories();

    List<BlogCategory> getAllCategories();
}
