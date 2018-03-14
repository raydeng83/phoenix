package com.phoenix.external.ssoapp.repository;

import com.phoenix.external.ssoapp.model.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

}
