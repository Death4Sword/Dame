package fr.death.ynov;

public class Map {
    private final Pawn[][] map = new Pawn[10][10];

    public void reset() {
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map.length; x++) {
                this.map[y][x] = null;
            }
        }
    }

    public Pawn getPawn(int x, int y) {
        return getPawn(new Vector2D(x, y));
    }

    public Pawn getPawn(Vector2D pos) {
        return this.map[pos.getY()][pos.getX()];
    }

    public void removePawn(Vector2D pos) {
        final Pawn pawn = getPawn(pos);
        if (pawn != null) {
            removePawn(pawn);
        }
    }

    public void removePawn(Pawn pawn) {
        pawn.getOwner().removePawn(pawn);
        this.map[pawn.getPosition().getY()][pawn.getPosition().getX()] = null;
    }

    public void spawnPawn(Player player, Vector2D pos) {
        final Pawn pawn = new Pawn(player, this);
        pawn.setPosition(pos);
        player.addPawn(pawn);
        this.map[pos.getY()][pos.getX()] = pawn;
    }

    public void spawnTeam(Player player, StartPosition startPosition) {
        int offsety = (startPosition == StartPosition.UP ? 0 : 6);
        for (int y = offsety; y < offsety + 4; y++) {
            for (int x = 0; x < this.map.length; x++) {
                if (player.getTeam().isPair() && x % 2 == 0) {
                    spawnPawn(player, new Vector2D(x + (y % 2), y));
                } else if (!player.getTeam().isPair() && x % 2 > 0) {
                    spawnPawn(player, new Vector2D(x - (y % 2 > 0 ? 0 : 1), y));
                }
            }
        }
    }

    public void movePawn(Vector2D pos, Vector2D newPos) {
        final Pawn pawn = getPawn(pos);
        if (pawn != null) {
            movePawn(pawn, newPos);
        }
    }

    public void movePawn(Pawn pawn, Vector2D newPos) {
        this.map[pawn.getPosition().getY()][pawn.getPosition().getX()] = null;
        pawn.setPosition(newPos);
        this.map[newPos.getY()][newPos.getX()] = pawn;
    }

    enum StartPosition {
        UP, DOWN
    }
}
