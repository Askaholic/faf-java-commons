package com.faforever.commons.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@AllArgsConstructor
public enum Faction {
    // Order is crucial
    AEON("aeon"),
    CYBRAN("cybran"),
    UEF("uef"),
    SERAPHIM("seraphim"),
    NOMAD("nomad"),
    CIVILIAN("civilian");

    private static final Map<String, Faction> fromString;

    static {
        fromString = new HashMap<>();
        for (Faction faction : values()) {
            fromString.put(faction.string, faction);
        }
    }

    private final String string;

    @JsonCreator
    public static Faction fromFaValue(int value) {
        return Faction.values()[value - 1];
    }

    public static Faction fromString(String string) {
        return fromString.get(string);
    }

    /**
     * Returns the faction value used as in "Forged Alliance Forever".
     */
    @JsonValue
    public int toFaValue() {
        return ordinal() + 1;
    }
}
