package com.rustedshark.mud.models.player;

import com.rustedshark.mud.models.entity.EntityApperance;
import com.rustedshark.mud.models.races.RaceEntity;
import com.rustedshark.mud.models.stats.CombatStats;
import com.rustedshark.mud.models.stats.TertiaryStats;

/**
 * Representation of a player in the game world, should contain everything needed for interaction
 */
public class Player {

    private EntityApperance _apperance;
    private RaceEntity _race;
    private TertiaryStats _stats;
    private CombatStats _combatStats = new CombatStats();

    public EntityApperance apperance() {
        return _apperance;
    }

    public RaceEntity race() {
        return _race;
    }

    public TertiaryStats stats() {
        return _stats;
    }

    public CombatStats combatStats() {
        return _combatStats.refreshStats(_stats);
    }

}
