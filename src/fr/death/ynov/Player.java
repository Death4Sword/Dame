package fr.death.ynov;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Team team;
    private final List<Pawn> pawnList = new ArrayList<>();

    public Player(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void addPawn(Pawn pawn) {
        this.pawnList.add(pawn);
    }

    public void removePawn(Pawn pawn) {
        this.pawnList.remove(pawn);
    }

    public boolean isDeath() {
        return this.pawnList.size() == 0;
    }

    public Pawn getPawn(Vector2D pos) {
        for (Pawn pawn : this.pawnList) {
            if (pawn.getPosition().equals(pos)) {
                return pawn;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Player{" +
                "team=" + team +
                '}';
    }
}
