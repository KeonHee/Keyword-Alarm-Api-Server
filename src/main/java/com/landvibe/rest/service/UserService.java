package com.landvibe.rest.service;

import com.landvibe.domain.User;
import com.landvibe.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-05-02.
 */
public interface UserService {

    User addUser(String name, String userId);

    List<User> findAll();

    User findById(long id);

}
