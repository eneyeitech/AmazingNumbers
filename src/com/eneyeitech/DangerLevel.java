package com.eneyeitech;

public enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    int level;

    DangerLevel(int l) {
        this.level = l;
    }

    int getLevel() {
        return this.level;
    }
}
