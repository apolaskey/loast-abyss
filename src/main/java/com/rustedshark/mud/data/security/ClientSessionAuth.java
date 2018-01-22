package com.rustedshark.mud.data.security;

/**
 * Contains session authentication information
 */
public class ClientSessionAuth {

    private final String _accountName;
    private final String _session;

    public ClientSessionAuth(String accountName, String sessionKey) {
        _accountName = accountName;
        _session = sessionKey;
    }

}
