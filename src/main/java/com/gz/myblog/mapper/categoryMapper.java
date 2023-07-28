package com.gz.myblog.mapper;

import com.gz.myblog.pojo.BlogCategory;
import com.gz.myblog.util.PageQueryUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-10:29
 * @VERSON:1.8
 */
@Repository
public interface categoryMapper {

    int getTotalCategories(PageQueryUtil pageQueryUtil);

    List<BlogCategory> findCategoryList(PageQueryUtil pageQueryUtil);

    BlogCategory selectByPrimaryKey(Integer blogCategoryId);

    int updateByPrimary(BlogCategory blogCategory);

}
