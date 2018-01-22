package com.rustedshark.mud.data.game.stats;

public class CoreStats {

    private int _strength = 1, _dexterity = 1, _constitution = 1, _intelligence = 1, _wisdom = 1, _charisma = 1, _luck = 0;

    public int getStrength() {
        return _strength;
    }
    public int getDexterity() {
        return _dexterity;
    }
    public int getConstitution() {
        return _constitution;
    }
    public int getIntelligence() {
        return _intelligence;
    }
    public int getWisdom() {
        return _wisdom;
    }
    public int getCharisma() {
        return _charisma;
    }
    public int getLuck() {
        return _luck;
    }

    public CoreStats setStrength(int value) {
        _strength = value;
        return this;
    }
    public CoreStats setDexterity(int value) {
        _dexterity = value;
        return this;
    }
    public CoreStats setConstitution(int value) {
        _constitution = value;
        return this;
    }
    public CoreStats setIntelligence(int value) {
        _intelligence = value;
        return this;
    }
    public CoreStats setWisdom(int value) {
        _wisdom = value;
        return this;
    }
    public CoreStats setCharisma(int value) {
        _charisma = value;
        return this;
    }
    public CoreStats setLuck(int value) {
        _luck = value;
        return this;
    }
}
