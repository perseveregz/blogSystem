package com.gz.myblog.mapper;

import com.gz.myblog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Auther:Mr.Guo
 * @create:2023/7/23-22:56
 * @VERSON:1.8
 */
@Repository
public interface UserMapper {


    /**
     * 登陆方法
     *
     * @param userName
     * @param password
     * @return
     */
     User login(@Param("userName") String userName, @Param("password") String password);

    /**
     * 根据Id获取用户信息
     * @param loginUserId
     * @return
     */
    User selectById(@Param("loginUserId") Integer loginUserId);

    /**
     * 根据id更新用户用户名
     * @param user
     * @return
     */
    int updateUserNameById(User user);

    /**
     * 根据id更新用户密码
     * @param user
     * @return
     */
    int updatePasswordById(User user);
//    User login(@Param("userName") String userName, @Param("password") String password);


}
