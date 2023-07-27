package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.commentMapper;
import com.gz.myblog.service.commentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:07
 * @VERSON:1.8
 */

@Service
public class commentServiceImp implements commentService {

    @Autowired
    private commentMapper commentMapper;

    @Override
    public int getTotalComments() {
        return commentMapper.getTotalComments(null);
    }
}
