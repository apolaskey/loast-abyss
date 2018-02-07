package com.rustedshark.mud.commands.profile;

import com.rustedshark.mud.commands.AbstractCommand;
import com.rustedshark.mud.commands.MudCommand;
import com.rustedshark.mud.data.game.GameSession;

@MudCommand
public class SaveCommand extends AbstractCommand {

    public SaveCommand(GameSession session) {
        super(session);
    }

    @Override
    public void execute() {

    }

}
