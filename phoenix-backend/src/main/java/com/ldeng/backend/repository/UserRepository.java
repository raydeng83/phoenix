package com.ldeng.backend.repository;

import com.ldeng.backend.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
