package com.gz.myblog.service;

import com.gz.myblog.pojo.Blog;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:05
 * @VERSON:1.8
 */
public interface blogService {

    /**
     * 获得所有的博客
     * @return
     */
    int getTotalBlogs();

    /**
     * 根据博客Id获取博客
     * @param blogId
     * @return
     */
    Blog getBlogById(Long blogId);

    /**
     * 修改文章
     * @param blog
     * @return
     */
    String updateBlog(Blog blog);
}
