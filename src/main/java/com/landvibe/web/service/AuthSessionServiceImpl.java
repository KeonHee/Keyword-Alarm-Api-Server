package com.landvibe.web.service;

import com.landvibe.domain.AuthSession;
import com.landvibe.web.repository.AuthSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2017-05-13.
 */

@Service("AuthSessionService")
public class AuthSessionServiceImpl implements AuthSessionService {

    @Autowired
    AuthSessionRepository authSessionRepository;

    @Override
    public AuthSession findLastOne() {
        List<AuthSession> authSessionList = authSessionRepository.findAll();
        if(authSessionList.isEmpty()){
            return null;
        }else{
            return authSessionList.get(authSessionList.size()-1);
        }
    }

    @Override
    public AuthSession addSession(AuthSession authSession) {
        return authSessionRepository.save(authSession);
    }

    @Override
    public void deleteAll() {
        authSessionRepository.deleteAll();
    }
}
