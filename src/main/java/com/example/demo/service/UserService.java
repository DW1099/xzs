package com.example.demo.service;

import com.example.demo.domain.KeyValue;
import com.example.demo.domain.User;
import com.example.demo.viewmodel.admin.user.UserPageRequestVM;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService extends BaseService<User> {
   List<User> getUsers();

   User getUserById(Integer id);

   User getUserByUserName(String username);

   User getUserByUserNamePwd(String username,String pwd);

   User getUserByUuid(String uuid);

   List<User> userPageList(String name,Integer pageIndex,Integer pageSize);

   Integer userPageCount(String name);

   PageInfo<User> userPage(UserPageRequestVM requestVM);

    void insertUser(User user);

    void updateUser(User user);

    void updateUsersAge(Integer age,List<Integer> ids);

    void deleteUserByIds(List<Integer> ids);


    Integer selectAllCount();


    List<KeyValue> selectByUserName(String userName);


    List<User> selectByIds(List<Integer> ids);

    User selectByWxOpenId(String wxOpenId);

    void changePicture(User user, String imagePath);
}
