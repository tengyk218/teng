package com.ncme.webapi.service.impl;

import com.ncme.webapi.dao.user.UserMapper;
import com.ncme.webapi.domain.User;
import com.ncme.webapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public User user(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
