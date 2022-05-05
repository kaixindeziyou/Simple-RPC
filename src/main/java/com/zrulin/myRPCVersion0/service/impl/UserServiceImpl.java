package com.zrulin.myRPCVersion0.service.impl;

import com.zrulin.myRPCVersion0.service.UserService;
import com.zrulin.myRPCVersion0.pojo.User;

import java.util.Random;
import java.util.UUID;

/**
 * @author zrulin
 * @create 2022-05-03 21:41
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById(int id) {
        System.out.println("客户端查询了id为："+id+"的用户");
        //模拟从数据库中取数据的行为
        Random random = new Random();
        User user = new User(id,UUID.randomUUID().toString(),random.nextBoolean());
        return user;
    }
}
