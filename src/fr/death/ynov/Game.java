package fr.death.ynov;

import java.util.Scanner;

public class Game {

    public void start() {
        final Map map = new Map();

        map.reset();



        final Player player1 = new Player(Team.WHITE);
        final Player player2 = new Player(Team.BLACK);

        map.spawnTeam(player1, Map.StartPosition.UP);
        map.spawnTeam(player2, Map.StartPosition.DOWN);

        displayMap(map);
        int turn = 0;
        while (!player1.isDeath() || !player2.isDeath()) {


            Team testTurn=teamTurn(map,player1,player2,turn);
            int posInit=scannerActualPawnPosition();
            int posInitbis =scannerActualPawnPosition();
                  if(map.getPawn(posInit,posInitbis).getOwner().getTeam()==testTurn){
                      System.out.println();
                      map.movePawn(map.getPawn(posInit,posInitbis),
                              new Vector2D(scannerNewPawnPosition(), scannerNewPawnPosition()));
                      turn+=1;
                  }
            displayMap(map);
        }
    }
    private Team teamTurn(Map map,Player White, Player Black, int turn){
        Team TeamTurn = Team.WHITE;
        String teamBlack= "Black ";
        String teamWhite= "White ";
        //while(!White.isDeath() || !Black.isDeath()){


            if(turn%2!=0){
            TeamTurn = Team.BLACK;
                System.out.print("C'est le tour de " + teamBlack);
                //turn+=1;
            return TeamTurn;
            }
            else{
                System.out.print("C'est le tour de " + teamWhite);
                //turn+=1;
                return TeamTurn;
            }


        //}
    }

    private int scannerActualPawnPosition(){
        System.out.println("veuillez choisir les coordonnées x et y du pion que vous souhaitez bouger :");
        Scanner sc = new Scanner(System.in);
        int pos1 = sc.nextInt();
        return pos1;
    }

    private int scannerNewPawnPosition(){
        System.out.println("Veuillez entrer les nouvelles coordonnées x, puis y du pion :");
        Scanner sc = new Scanner(System.in);
        int pos2 = sc.nextInt();
        return pos2;
    }

    private void displayMap(Map map) {
        for (int y = 0; y < 10; y++) {
            System.out.print(y + "|");
            for (int x = 0; x < 10; x++) {
                final Pawn pawn = map.getPawn(x, y);
                if (pawn != null) {
                    System.out.print(pawn.getOwner().getTeam().getSymbole());
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("|\n");
        }
    }
}

