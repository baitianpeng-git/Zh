package com.zh.btp.service;

import com.zh.btp.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.btp.mapper.UserMapper;

import javax.annotation.Resource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
public interface UserService extends IService<User> {

    User loginInfo(String username,String password);

}
