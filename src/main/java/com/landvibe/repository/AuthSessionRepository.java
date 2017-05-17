package com.landvibe.repository;

import com.landvibe.domain.AuthSession;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by user on 2017-05-13.
 */
public interface AuthSessionRepository extends JpaRepository<AuthSession,Long> {
}
