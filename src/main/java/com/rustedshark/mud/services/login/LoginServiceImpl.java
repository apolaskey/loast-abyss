package com.rustedshark.mud.services.login;

import com.rustedshark.mud.data.persistence.GameAccountDao;
import com.rustedshark.mud.data.security.ClientSessionAuth;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final Jdbi _jbdi;

    @Autowired public LoginServiceImpl(Jdbi repo) {
        _jbdi = repo;
    }

    @Override
    public ClientSessionAuth processCredential(String accountName, String password) {

        return null;
    }

}
