package com.zh.btp.service.impl;

import com.zh.btp.entity.User;
import com.zh.btp.mapper.UserMapper;
import com.zh.btp.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User loginInfo(String username, String password) {
        return userMapper.login(username,password);
    }
}
