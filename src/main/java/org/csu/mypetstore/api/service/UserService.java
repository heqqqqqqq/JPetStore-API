package org.csu.mypetstore.api.service;

import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.User;

public interface UserService {
    CommonResponse<User> findUserByUsername(String username);
    CommonResponse<User> findUser(String username,String password);//用于登录
}
