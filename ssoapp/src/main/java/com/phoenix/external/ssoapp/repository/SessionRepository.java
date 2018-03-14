package com.phoenix.external.ssoapp.repository;

import com.phoenix.external.ssoapp.model.Session;
import org.springframework.data.repository.CrudRepository;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Session findByJsessionId(String jsessionId);
}
