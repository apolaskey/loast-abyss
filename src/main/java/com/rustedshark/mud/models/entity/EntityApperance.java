package com.rustedshark.mud.models.entity;

public interface EntityApperance {
    default String firstName() {
        return "Formless";
    }
    default String lastName() {
        return "";
    }
    default String description() {
        return "A formless being.";
    }
}
