package com.gz.myblog.mapper;

import com.gz.myblog.util.PageQueryUtil;
import org.springframework.stereotype.Repository;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:14
 * @VERSON:1.8
 */

@Repository
public interface tagMapper {

    int getTotalTags(PageQueryUtil pageQueryUtil);
}
