package com.gz.myblog.controller;

import com.gz.myblog.pojo.Blog;
import com.gz.myblog.service.blogService;
import com.gz.myblog.service.categoryService;
import com.gz.myblog.util.Result;
import com.gz.myblog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 博客管理页面
 * @Auther:Mr.Guo
 * @create:2023/7/27-10:53
 * @VERSON:1.8
 */
@Controller
@RequestMapping("/admin")
public class blogController {

    @Resource
    private categoryService categoryService;

    @Resource
    private blogService blogService;

    /*
    博客首页
     */
    @GetMapping("/blogs")
    public String blogList(HttpServletRequest request){
        request.setAttribute("path","blogs");
        return "/admin/blog";
    }

    /*
    发布博客
     */
    @GetMapping("/blogs/edit")
    public String editBlog(HttpServletRequest request){
        request.setAttribute("path","edit");
        //在发布博客时将所有存在的分类展示在发布界面
        request.setAttribute("categories", categoryService.getAllCategories());
        return "/admin/edit";
    }

    /*
    编辑某一个blogId的博客
     */
    @GetMapping("/blogs/eidt/{blogId}")
    public String editBlog(HttpServletRequest request, @PathVariable("blogId") Long blogId){
        //先将编辑页面突出显示
        request.setAttribute("path","edit");
        Blog blog = blogService.getBlogById(blogId);
        if (null == blog){
            return "error/error_400";
        }
        request.setAttribute("blog",blog);
        request.setAttribute("categories",categoryService.getAllCategories());
        return "/admin/edit";
    }

    /*
    修改博客
     */
    @PostMapping("/blog/update")
    @ResponseBody
    public Result updateBlog(@RequestParam("blogId") Long blogId,
                             @RequestParam("/blogTitle") String blogTitle,
                             @RequestParam(name = "blogSubUrl", required = false) String blogSubUrl,
                             @RequestParam("blogCategoryId") Integer blogCategoryId,
                             @RequestParam("/blogTags") String blogTags,
                             @RequestParam("/blogContent") String blogContent,
                             @RequestParam("/blogCoverImage") String blogCoverImage,
                             @RequestParam("/blogStatus") Byte blogStatus,
                             @RequestParam("/enableComment") Byte enableComment){
        if (StringUtils.isEmpty(blogTitle)){
            return ResultGenerator.genFailResult("请输入文章标题");
        }
        //检查标题去掉首尾空格后的长度的是否大于150
        if (blogTitle.trim().length() > 150){
            return ResultGenerator.genFailResult("标题过长");
        }
        if (blogSubUrl.trim().length() > 150){
            return ResultGenerator.genFailResult("路径过长");
        }
        if (StringUtils.isEmpty(blogTags)){
            return ResultGenerator.genFailResult("请输入文章标签");
        }
        if (blogTags.trim().length() > 150){
            return ResultGenerator.genFailResult("标签过长");
        }
        if (StringUtils.isEmpty(blogCoverImage)){
            return ResultGenerator.genFailResult("封面图不能为空");
        }
        if (blogContent.trim().length() > 100000){
            return ResultGenerator.genFailResult("文章内容过长");
        }
        if (StringUtils.isEmpty(blogContent)){
            return ResultGenerator.genFailResult("请输入文章内容");
        }
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setBlogTags(blogTitle);
        blog.setBlogSubUrl(blogSubUrl);
        blog.setBlogCategoryId(blogCategoryId);
        blog.setBlogTags(blogTags);
        blog.setBlogContent(blogContent);
        blog.setBlogCoverImage(blogCoverImage);
        blog.setBlogStatus(blogStatus);
        blog.setEnableComment(enableComment);
        String updateBlogResult = blogService.updateBlog(blog);
        if ("success".equals(updateBlogResult)){
            return ResultGenerator.genSuccessResult("修改成功");
        } else {
            return ResultGenerator.genFailResult(updateBlogResult);
        }
    }

}
