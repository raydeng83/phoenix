package com.ldeng.backend.fr.openam;

import com.ldeng.backend.model.User;

public interface AMUserService {
    void createUser (User user);

    void authenticateUser (User user);
}
