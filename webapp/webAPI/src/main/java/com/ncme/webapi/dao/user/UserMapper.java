package com.ncme.webapi.dao.user;

import com.ncme.webapi.domain.User;

public interface UserMapper {

    User selectByPrimaryKey(String id);

}
