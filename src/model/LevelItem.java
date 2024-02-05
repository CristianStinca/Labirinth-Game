package model;

public enum LevelItem {
    DESTINATION('.'), WALL('#'), EMPTY(' ');
    LevelItem(char rep){ representation = rep; }
    public final char representation;
}
