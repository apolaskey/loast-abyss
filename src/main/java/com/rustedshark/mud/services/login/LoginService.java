package com.rustedshark.mud.services.login;

import com.rustedshark.mud.models.ClientSessionCredential;

public interface LoginService {
    ClientSessionCredential processCredential();

}
