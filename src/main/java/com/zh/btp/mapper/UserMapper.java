package com.zh.btp.mapper;

import com.zh.btp.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author btp
 * @since 2022-11-23
 */
public interface UserMapper extends BaseMapper<User> {

    User login(String username,String password);

}
