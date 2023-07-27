package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.blogMapper;
import com.gz.myblog.service.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:06
 * @VERSON:1.8
 */

@Service
public class blogServiceImp implements blogService {

    @Autowired
    private blogMapper blogMapper;

    @Override
    public int getTotalBlogs() {
        return blogMapper.getTotalBlogs(null);
    }
}
