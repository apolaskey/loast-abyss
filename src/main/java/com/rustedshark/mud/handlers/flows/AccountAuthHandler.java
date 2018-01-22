package com.rustedshark.mud.handlers.flows;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountAuthHandler {

    @Autowired
    private Jdbi _repo;



}
