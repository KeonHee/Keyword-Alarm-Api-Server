package com.landvibe.rest.service;

import com.landvibe.rest.domain.User;
import com.landvibe.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-05-02.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(String name, String userId){
        User user = new User(name,userId);
        return userRepository.save(user);
    }

    public List<User> findall(){
        return  userRepository.findAll();
    }

    public User findById(long id){
        return userRepository.findOne(id);
    }

}
