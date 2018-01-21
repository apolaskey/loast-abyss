package com.rustedshark.mud.models.stats;

public class TertiaryStats {

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

    public TertiaryStats setStrength(int value) {
        _strength = value;
        return this;
    }
    public TertiaryStats setDexterity(int value) {
        _dexterity = value;
        return this;
    }
    public TertiaryStats setConstitution(int value) {
        _constitution = value;
        return this;
    }
    public TertiaryStats setIntelligence(int value) {
        _intelligence = value;
        return this;
    }
    public TertiaryStats setWisdom(int value) {
        _wisdom = value;
        return this;
    }
    public TertiaryStats setCharisma(int value) {
        _charisma = value;
        return this;
    }
    public TertiaryStats setLuck(int value) {
        _luck = value;
        return this;
    }
}
