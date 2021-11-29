package fr.death.ynov;

public class Pawn {
    private final Player owner;
    private Vector2D position;

    public Pawn(Player owner, Map map) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Pawn{" +
                "owner=" + owner +
                ", position=" + position +
                '}';
    }
}
