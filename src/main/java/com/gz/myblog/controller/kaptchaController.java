package com.gz.myblog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/24-10:13
 * @VERSON:1.8
 */

@Controller
public class kaptchaController {

    @GetMapping("/common/kaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("/image/png");

        ShearCaptcha shearCaptcha = CaptchaUtil.createShearCaptcha(150, 30, 4, 2);

        //验证码存入session
        request.getSession().setAttribute("verifyCode",shearCaptcha);

        //输出图片流
        shearCaptcha.write(response.getOutputStream());
    }

}
