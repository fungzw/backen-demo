package com.zwfung.mybatis.mapper;

import com.zwfung.mybatis.entity.User;

import java.util.List;

/**
 * @author zw fung
 */
public interface UserMapper {
    List<User> selectById(User user);
}
