package com.gz.myblog.service;

import com.gz.myblog.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/23-22:48
 * @VERSON:1.8
 */

@Service
public interface adminUserService {

    /**
     * 获取登录用户信息
     * @param uesrName
     * @param password
     * @return
     */
    User login(String uesrName, String password);

    /**
     * 通过id获取用户信息
     * @param loginUserId
     * @return
     */
    User getUserDetailById(Integer loginUserId);

    /**
     * 修改用户名
     * @param loginUserId
     * @param loginUserName
     * @param nickName
     * @return
     */
    boolean updateName(Integer loginUserId, String loginUserName, String nickName);

    /**
     * 修改密码
     * @param loginUserId
     * @param originalPassword
     * @param newPassword
     * @return
     */
    boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword);
}
