package com.rustedshark.mud.data.game.player;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;

/**
 * Representation of a players connected session, contains game information and non-game information
 */
public class PlayerSession {
    private String _playerFirstName;
    private String _playerSurname;
    private String _playerDescription;

    @Inject
    private Jdbi _repo;

    public PlayerSession(Jdbi repo) {
        String b = "";
    }

}
