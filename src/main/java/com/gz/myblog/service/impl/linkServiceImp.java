package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.linkMapper;
import com.gz.myblog.service.linkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:07
 * @VERSON:1.8
 */
@Service
public class linkServiceImp implements linkService {

    @Autowired
    private linkMapper linkMapper;

    @Override
    public int getTotalLinks() {
        return linkMapper.getAllLinks(null);
    }
}
