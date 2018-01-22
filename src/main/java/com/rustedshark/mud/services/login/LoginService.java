package com.rustedshark.mud.services.login;

import com.rustedshark.mud.data.security.ClientSessionAuth;

public interface LoginService {
    ClientSessionAuth processCredential(String accountName, String password);
}
