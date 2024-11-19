package com.davidwerfelmann.course_scheduler.models;

public enum Area {
    DEPARTMENTAL("Departmental"),
    KEYBOARD_STUDIES("Keyboard Studies"),
    INSTRUMENTAL_STUDIES("Instrumental Studies"),
    VOCAL_PERFORMANCE("Vocal Performance and Opera"),
    COMPOSITION("Composition and Songwriting"),
    MUSIC_DIRECTION_FOR_MUSICAL_THEATRE("MDMT"),
    ENSEMBLES("Ensembles"),
    THEORY("Theory"),
    JAZZ_STUDIES("Jazz Studies"),
    GLOBAL_CITIZENSHIP_PROGRAM("GCP"),
    MISCELLANEOUS("Misc."),
    MUSIC_EDUCATION("Music Ed."),
    MUSICOLOGY("Musicology"),
    ENTREPRENEURSHIP("Entrepreneurship"),
    CONDUCTING("Conducting"),
    BACHELOR_OF_ARTS("BA in Music");

    private final String displayName;

    Area(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
