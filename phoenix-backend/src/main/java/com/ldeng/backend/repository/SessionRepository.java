package com.ldeng.backend.repository;

import com.ldeng.backend.model.Session;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SessionRepository extends CrudRepository<Session, Long> {
    Session findByJsessionId(String jsessionId);
}
