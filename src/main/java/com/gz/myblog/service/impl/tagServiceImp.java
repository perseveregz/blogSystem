package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.tagMapper;
import com.gz.myblog.service.tagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:07
 * @VERSON:1.8
 */
@Service
public class tagServiceImp implements tagService {

    @Autowired
    private tagMapper tagMapper;

    @Override
    public int getTotalTags() {
        return tagMapper.getTotalTags(null);
    }
}
