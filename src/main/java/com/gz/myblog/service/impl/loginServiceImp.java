package com.gz.myblog.service.impl;

import com.gz.myblog.mapper.UserMapper;
import com.gz.myblog.pojo.User;
import com.gz.myblog.service.adminUserService;

import com.gz.myblog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/23-22:48
 * @VERSON:1.8
 */

@Service
public class loginServiceImp implements adminUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String password) {
        //对密码进行MD5加密；为了在数据库中与已注册的密码匹配
        String passwordMD5 = MD5Util.MD5Encode(password, "UTF-8");
        return userMapper.login(userName,passwordMD5);
    }

    @Override
    public User getUserDetailById(Integer loginUserId) {
        return userMapper.selectById(loginUserId);
    }

    @Override
    public boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        User user = userMapper.selectById(loginUserId);
        if (user != null){
            //将新的用户名设置到数据库中
            user.setLoginUserName(loginUserName);
            user.setNickName(nickName);
            if (userMapper.updateUserNameById(user) > 1){
                //修改成功返回true
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        User user = userMapper.selectById(loginUserId);
        if (null != user){
            String originalPasswordMD5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMD5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            if (originalPasswordMD5.equals(user.getLoginPassword())){
                user.setLoginPassword(newPasswordMD5);
                //修改数据库中的信息
                if (userMapper.updatePasswordById(user) > 0){
                    return true;
                }
            }
        }
        return false;
    }
}
