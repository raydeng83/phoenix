package com.ldeng.backend.repository;

import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserSession;
import org.springframework.data.repository.CrudRepository;

public interface UserSessionRepository extends CrudRepository<UserSession, Long> {
    UserSession findByUser(User user);
}
