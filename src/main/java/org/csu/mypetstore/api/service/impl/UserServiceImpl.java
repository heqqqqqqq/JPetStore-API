package org.csu.mypetstore.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.csu.mypetstore.api.common.CommonResponse;
import org.csu.mypetstore.api.entity.User;
import org.csu.mypetstore.api.persistence.UserMapper;
import org.csu.mypetstore.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public CommonResponse<User> findUserByUsername(String username) {
        User user=userMapper.selectById(username);
        if(user==null){
            return CommonResponse.createForError("获取用户信息失败");
        }
        return CommonResponse.createForSuccess(user);
    }

    @Override
    public CommonResponse<User> findUser(String username, String password) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        queryWrapper.eq("password",password);

        User user=userMapper.selectOne(queryWrapper);
        if(user==null){
            return CommonResponse.createForError("用户名或密码不正确");//注意为error
        }
        return findUserByUsername(username);
    }
}
