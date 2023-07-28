package com.gz.myblog.mapper;

import com.gz.myblog.pojo.Blog;
import com.gz.myblog.util.PageQueryUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:14
 * @VERSON:1.8
 */

@Repository
public interface blogMapper {

     int getTotalBlogs(PageQueryUtil pageQueryUtil);

     Blog getBlogById(@Param("blogId") Long blogId);

     int updateByPrimaryKey(Blog blogForUpdate);
}
