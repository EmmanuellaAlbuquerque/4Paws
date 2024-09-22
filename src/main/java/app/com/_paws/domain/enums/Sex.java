package app.com._paws.domain.enums;

import lombok.Getter;

@Getter
public enum Sex {
    FEMEA("fêmea"),
    MACHO("macho");

    private final String name;

    Sex(String name) {
        this.name = name;
    }
}