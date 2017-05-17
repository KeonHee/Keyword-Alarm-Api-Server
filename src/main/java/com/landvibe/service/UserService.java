package com.landvibe.service;

import com.landvibe.domain.User;

import java.util.List;

/**
 * Created by user on 2017-05-02.
 */
public interface UserService {

    User addUser(String name, String userId);

    List<User> findAll();

    User findById(long id);

}
