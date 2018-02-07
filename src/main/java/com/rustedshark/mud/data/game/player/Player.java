package com.rustedshark.mud.data.game.player;

import com.rustedshark.mud.data.game.entity.Background;
import com.rustedshark.mud.data.game.races.RaceEntity;
import com.rustedshark.mud.data.game.stats.CombatStats;
import com.rustedshark.mud.data.game.stats.CoreStats;

/**
 * Representation of a player in the game world, should contain everything needed for interaction
 */
public class Player {

    private Background background;
    private RaceEntity _race;
    private CoreStats _stats;
    private CombatStats _combatStats = new CombatStats();

    public Background apperance() {
        return background;
    }

    public RaceEntity race() {
        return _race;
    }

    public CoreStats stats() {
        return _stats;
    }

    public CombatStats combatStats() {
        return _combatStats.refreshStats(_stats);
    }

}
