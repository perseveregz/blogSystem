package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.BlogTagRelationMapper;
import com.gz.myblog.mapper.blogMapper;
import com.gz.myblog.mapper.categoryMapper;
import com.gz.myblog.mapper.tagMapper;
import com.gz.myblog.pojo.Blog;
import com.gz.myblog.pojo.BlogCategory;
import com.gz.myblog.pojo.BlogTag;
import com.gz.myblog.pojo.BlogTagRelation;
import com.gz.myblog.service.blogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-11:06
 * @VERSON:1.8
 */

@Service
public class blogServiceImp implements blogService {

    @Autowired
    private blogMapper blogMapper;

    @Autowired
    private categoryMapper categoryMapper;

    @Autowired
    private tagMapper tagMapper;

    @Autowired
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public int getTotalBlogs() {
        return blogMapper.getTotalBlogs(null);
    }

    @Override
    public Blog getBlogById(Long blogId) {
        return blogMapper.getBlogById(blogId);
    }

    @Override
    public String updateBlog(Blog blog) {
        Blog blogForUpdate = blogMapper.getBlogById(blog.getBlogId());
        if (null == blogForUpdate){
            return "数据不存在";
        }
        blogForUpdate.setBlogTitle(blog.getBlogTitle());
        blogForUpdate.setBlogSubUrl(blog.getBlogSubUrl());
        blogForUpdate.setBlogContent(blog.getBlogContent());
        blogForUpdate.setBlogCoverImage(blog.getBlogCoverImage());
        blogForUpdate.setBlogStatus(blog.getBlogStatus());
        blogForUpdate.setEnableComment(blog.getEnableComment());
        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
        if (null == blogCategory){
            blogForUpdate.setBlogCategoryId(0);
            blogForUpdate.setBlogCategoryName("默认分类");
        }else {
            //设置博客分类名称
            blogForUpdate.setBlogCategoryName(blogCategory.getCategoryName());
            blogForUpdate.setBlogCategoryId(blogCategory.getCategoryId());
            //分类排序+1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        //处理标签
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6){
            return "标签数量最多为6";
        }
        blogForUpdate.setBlogTags(blog.getBlogTags());
        //新增的tag对象：存储新的tag标签
        ArrayList<BlogTag> tagListForInsert = new ArrayList<>();
        //所有的tag对象：存储数据库中的的tag标签
        ArrayList<BlogTag> allTagsList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            BlogTag tag = tagMapper.getByTagName(tags[i]);
            if (null == tag){
                //说明该标签不存在，将其新增到数据库
                BlogTag tempTag = new BlogTag();
                tempTag.setTagName(tags[i]);
                tagListForInsert.add(tempTag);
            }else {
                allTagsList.add(tag);
            }
        }
        //将新增的tag添加到数据库
        if (!CollectionUtils.isEmpty(tagListForInsert)){
            tagMapper.batchInsertBlogTag(tagListForInsert);
        }
        ArrayList<BlogTagRelation> blogTagRelations = new ArrayList<>();
        //新增关系数据
        allTagsList.addAll(tagListForInsert);
        for (BlogTag tag : allTagsList){
            BlogTagRelation blogTagRelation = new BlogTagRelation();
            blogTagRelation.setBlogId(blog.getBlogId());
            blogTagRelation.setTagId(tag.getTagId());
            blogTagRelations.add(blogTagRelation);
        }
        //修改blog信息
        if (blogCategory != null){
            categoryMapper.updateByPrimary(blogCategory);
        }
        blogTagRelationMapper.deleteByBlogId(blog.getBlogId());
        blogTagRelationMapper.batchInsert(blogTagRelations);
        if (blogMapper.updateByPrimaryKey(blogForUpdate) > 0){
            return "success";
        }
        return "修改失败";
    }
}
