package com.ncme.auth.mapper;

import com.ncme.auth.model.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ralap on 2017/11/9.
 */
@Mapper
public interface UserMapper {

    @Select("select account_name as name,account_password as password,account_id as id from system_account where account_name = #{account_name}")
    UserModel getUserByName(String name);
}
