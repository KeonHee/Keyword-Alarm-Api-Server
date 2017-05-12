package com.landvibe.web.service;

import com.landvibe.domain.AuthSession;

/**
 * Created by user on 2017-05-13.
 */
public interface AuthSessionService {

    AuthSession findLastOne();

    AuthSession addSession(AuthSession authSession);

    void deleteAll();

}
