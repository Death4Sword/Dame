package fr.death.ynov;

public enum Team {
    WHITE('W'), BLACK('B'),NULL('N');

    private final char symbole;

    Team(char symbole) {
        this.symbole = symbole;
    }

    public char getSymbole() {
        return symbole;
    }

    public boolean isPair() {
        return this == WHITE;
    }
}
