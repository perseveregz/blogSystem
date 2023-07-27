package com.gz.myblog.mapper;

import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:14
 * @VERSON:1.8
 */

@Repository
public interface commentMapper {

    int getTotalComments(Map map);
}
