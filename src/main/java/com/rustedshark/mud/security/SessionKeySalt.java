package com.rustedshark.mud.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Simple salting of sensitive data
 */
public class SessionKeySalt {

    private static final PasswordEncoder _passwordEncoder = new BCryptPasswordEncoder();

    public static String doSalt(String value) {
        return _passwordEncoder.encode(value);
    }

    public static boolean isValidKey(String raw, String encoded) {
        return _passwordEncoder.matches(raw, encoded);
    }

}
