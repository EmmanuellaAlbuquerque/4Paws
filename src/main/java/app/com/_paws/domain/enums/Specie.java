package app.com._paws.domain.enums;

import lombok.Getter;

@Getter
public enum Specie {
    FELINA("felina"),
    CANINA("canina");

    private final String name;

    Specie(String name) {
        this.name = name;
    }
}
