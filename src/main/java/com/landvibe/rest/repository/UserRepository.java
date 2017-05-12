package com.landvibe.rest.repository;

import com.landvibe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2017-05-02.
 */
public interface UserRepository extends JpaRepository<User,Long> {



}
