package com.zwfung.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zwfung.entity.User;
import com.zwfung.mapper.UserMapper;
import com.zwfung.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author zw fung
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }
}
