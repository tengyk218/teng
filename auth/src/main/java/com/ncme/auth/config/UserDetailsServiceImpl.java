package com.ncme.auth.config;

import com.ncme.auth.model.UserModel;
import com.ncme.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YooLin1c
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel = userService.getUserByName(username);
        if (userModel == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        return new User(userModel.getName(), "{MD5}" + userModel.getPassword(),
                createAuthority(userModel.getRoles()));
    }

    private List<SimpleGrantedAuthority> createAuthority(String roles) {
        roles = "admin,user,select";
        String[] roleArray = roles.split(",");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (String role : roleArray) {
            authorityList.add(new SimpleGrantedAuthority(role));
        }
        return authorityList;
    }
}
