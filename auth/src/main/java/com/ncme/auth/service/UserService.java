package com.ncme.auth.service;

import com.ncme.auth.mapper.UserMapper;
import com.ncme.auth.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ralap on 2017/11/9.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserModel getUserByName(String name) {
        return userMapper.getUserByName(name);
    }
}
