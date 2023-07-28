package com.gz.myblog.mapper;

import com.gz.myblog.pojo.BlogTagRelation;

import java.util.ArrayList;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/28-16:53
 * @VERSON:1.8
 */
public interface BlogTagRelationMapper {


    int deleteByBlogId(Long blogId);

    int batchInsert(ArrayList<BlogTagRelation> blogTagRelations);
}
