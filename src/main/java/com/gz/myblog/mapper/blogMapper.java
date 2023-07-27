package com.gz.myblog.mapper;

import com.gz.myblog.util.PageQueryUtil;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:14
 * @VERSON:1.8
 */
public interface blogMapper {

     int getTotalBlogs(PageQueryUtil pageQueryUtil);
}
