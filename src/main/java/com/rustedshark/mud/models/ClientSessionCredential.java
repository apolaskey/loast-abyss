package com.rustedshark.mud.models;

/**
 * Contains session authentication information
 */
public class ClientSessionCredential {
    private String username, password, sessionPublicKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Key to be used post-authentication with the client
     * @return {@link String} Hash
     */
    public String getSessionPublicKey() {
        return sessionPublicKey;
    }

    public void setSessionPublicKey(String sessionPublicKey) {
        this.sessionPublicKey = sessionPublicKey;
    }
}
