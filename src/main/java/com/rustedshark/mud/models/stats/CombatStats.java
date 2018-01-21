package com.rustedshark.mud.models.stats;

public class CombatStats {

    private int _maxHp = 20, _maxMana = 100, _maxMovement = 100, _maxInitiative = 1;
    private int _hp = _maxHp, _mana = _maxMana, _movement = _maxMovement, _initiative = _maxInitiative;

    public CombatStats refreshStats(TertiaryStats tertiaryStats) {
        return this;
    }

    public int currentHp() {
        return _hp;
    }

    public int currentMana() {
        return _mana;
    }

    public int currentMovement() {
        return _movement;
    }

    public int currentInitiative() {
        return _initiative;
    }

    public int maxHp() {
        return _maxHp;
    }

    public int maxMana() {
        return _maxMana;
    }

    public int maxMovement() {
        return _maxMovement;
    }

    public int maxInitiative() {
        return _maxInitiative;
    }

}
