package com.gz.myblog.controller;

import cn.hutool.captcha.ShearCaptcha;
import com.gz.myblog.pojo.User;
import com.gz.myblog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/23-19:41
 * @VERSON:1.8
 */

@Controller
@RequestMapping("/admin")
public class loginController {

    @Autowired
    private adminUserService adminUserService;

    @Autowired
    private categoryService categoryService;

    @Autowired
    private commentService commentService;

    @Autowired
    private blogService blogService;

    @Autowired
    private tagService tagService;

    @Autowired
    private linkService linkService;



    //跳转到登陆界面
    @GetMapping("/login")
    public String login(){
        return "/admin/login";
    }

    //前端登录页面使用的是button type="submit"，此时发送Post请求
    //原理：以POST发送请求的方式就是使用按钮（submit，button）和form的结合
    //超链接（a标签）默认是以GET的方式发送请求的，这里我们可以来验证一下
    //处理登录时传入的用户名、密码、验证码
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){

        if (!StringUtils.hasText(verifyCode)){
            session.setAttribute("errorMsg","验证码不能为空");
            return "/admin/login";
        }
        if (!StringUtils.hasText(userName) || !StringUtils.hasText(password)){
            session.setAttribute("errorMsg","用户名或密码不能为空");
            return "/admin/login";
        }
        //创建的验证码字符串会存到HttpSession中
        //拿到session中的验证码和url传来的验证码相比较即可
        ShearCaptcha shearCaptcha = (ShearCaptcha) session.getAttribute("verifyCode");
        if (null == shearCaptcha || !shearCaptcha.verify(verifyCode)){
            session.setAttribute("errorMsg","验证码错误");
            return "/admin/login";
        }
        User user = adminUserService.login(userName,password);
        if (null != user){
            session.setAttribute("loginUser",user.getNickName());
            session.setAttribute("loginUserId",user.getAdminUserId());
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg","登陆失败");
            return "/admin/login";
        }
    }

    //登陆后的首页，也是管理首页
    @GetMapping({"/","/index"})
    public String index(HttpServletRequest request){
        request.setAttribute("path","index");
        request.setAttribute("blogCount",blogService.getTotalBlogs());
        request.setAttribute("commentCount",commentService.getTotalComments());
        request.setAttribute("categoryCount",categoryService.getTotalCategories());
        request.setAttribute("tagCount",tagService.getTotalTags());
        request.setAttribute("linCount",linkService.getTotalLinks());
        return "/admin/index";
    }

    /*
    修改密码界面
     */
    @GetMapping("/profile")
    public String profile(HttpServletRequest request){
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        User user = adminUserService.getUserDetailById(loginUserId);
        if (null == user){
            return "/admin/login";
        }
        //这是为了点击修改密码时的 “突出显示”
        request.setAttribute("path","profile");
        //这两个是为了修改密码页面中的用户名和昵称显示
        request.setAttribute("loginUserName",user.getLoginUserName());
        request.setAttribute("nickName",user.getNickName());
        return "/admin/profile";
    }

    /*
    修改密码中的：改用户名
     */
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request,@RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName){
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)){
            return "参数不能为空";
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId,loginUserName,nickName)){
            return "success";
        }else {
            return "修改失败";
        }
    }

    /*
    修改密码中的：改密码
     */
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request,@RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword){
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)){
            return "参数不能为空";
        }
        Integer loginUserId = (Integer) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId,originalPassword,newPassword)){
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            //删除成功后，前端控制跳转至登陆页面
            return "success";
        }else {
            return "密码修改失败";
        }
    }

    /*
        退出登录
     */
    @GetMapping("/logout")
    public String logOut(HttpServletRequest request){
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "/admin/login";
    }

}
