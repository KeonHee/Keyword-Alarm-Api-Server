package com.landvibe.service;

import com.landvibe.domain.User;
import com.landvibe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-05-13.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User addUser(String name, String userId){
        User user = new User(name,userId);
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return  userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findOne(id);
    }
}
