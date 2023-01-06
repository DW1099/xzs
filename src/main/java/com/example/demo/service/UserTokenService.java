package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserToken;
import com.example.demo.service.BaseService;

public interface UserTokenService extends BaseService<UserToken> {

    UserToken bind(User user);

    UserToken checkBind(String openId);

    /**
     * 根据token获取UserToken，带缓存的
     *
     * @param token token
     * @return UserToken
     */
    UserToken getToken(String token);

    /**
     * 插入用户Token
     *
     * @param user user
     * @return UserToken
     */
    UserToken insertUserToken(User user);

    /**
     * 微信小程序退出，清除缓存
     *
     * @param userToken userToken
     */
    void unBind(UserToken userToken);
}
