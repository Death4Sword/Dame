package fr.death.ynov;

import java.util.Scanner;

public class Game {

    /*
    TODO: CHANGER LE CODE D'ANTHO IL EST MOCHE --> OPTIMISATION
    TODO: Manger les pions adverses
    TODO: Condition de victoire/défaite
    TODO: Menu Joueur Seul ou 2 Joueur --> Séléction 1 joueurs ou 2, explications des règles rapidement,
     et sortir du jeu
    TODO: Faire du 1 joueur --> code boucle sur pions "IA" et check si mouvement possible

     */
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
            Vector2D sideStep= scannerActualPawnPosition();
                  if(map.getPawn(sideStep).getOwner().getTeam()==testTurn){
                      Vector2D newPosition=scannerNewPawnPosition(testTurn,sideStep.getX(),sideStep.getY());
                      map.movePawn(map.getPawn(sideStep),newPosition);
                        if(newPosition!=sideStep){
                            turn+=1;
                        }
                  }
            displayMap(map);
        }
    }

    private Team teamTurn(Map map,Player White, Player Black, int turn){
        Team TeamTurn = Team.WHITE;
        String teamBlack= "Black ";
        String teamWhite= "White ";
            if(turn%2!=0){
            TeamTurn = Team.BLACK;
                System.out.print("C'est le tour de " + teamBlack);
            }
            else{
                System.out.print("C'est le tour de " + teamWhite);
            }
        return TeamTurn;


        //}
    }

    private Vector2D scannerActualPawnPosition(){
        Vector2D initPos= new Vector2D(0,0);
        System.out.println("veuillez choisir la coordonnée x  du pion que vous souhaitez bouger :");
        Scanner sc = new Scanner(System.in);
        int pos1 = sc.nextInt();
        System.out.println("veuillez choisir la coordonnée y  du pion que vous souhaitez bouger :");
        Scanner sc2 = new Scanner(System.in);
        int pos2= sc.nextInt();
        initPos.setX(pos1);
        initPos.setY(pos2);
        return initPos;
    }

    private Vector2D scannerNewPawnPosition(Team team, int x, int y){
        Vector2D testPos= new Vector2D(x,y);
        Vector2D newPos= new Vector2D(0,0);



            //Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez choisir vos mouvements de pions :" +
                    "\n1 - Diagonale Gauche" +
                    "\n2 - Diagonale Droite");
            System.out.println("OK");
        Scanner sc = new Scanner(System.in);
        int pos2 = sc.nextInt();
        int testX= x+1;
        int testNeg= x-1;
        if(testX>9 && pos2!=1){
        System.out.println("Vos seuls mouvements possibles:" +
            "\n1 - Diagonale Gauche");
        pos2 = sc.nextInt();
        }
        if(testNeg<0 && pos2!=2){
            System.out.println("Vos seuls mouvements possibles:" +
                    "\n2 - Diagonale Droite");
            pos2 = sc.nextInt();
        }
                //newPos.setX(x);
                //newPos.setY(y);
        /*switch (team) {
            case WHITE:
                switch (pos2) {
                    case 1:
                        newPos.setX(x - 1);
                        newPos.setY(y + 1);
                        break;
                    case 2:
                        newPos.setX(x + 1);
                        newPos.setY(y + 1);
                        break;
                    default:
                        newPos.setX(x);
                        newPos.setY(y);
                        System.out.println("Le pion ne peut pas sortir du tableau !");

                }
            case BLACK:
                switch (pos2) {
                    case 1:
                        newPos.setX(x - 1);
                        newPos.setY(y - 1);
                        break;
                    case 2:
                        newPos.setX(x + 1);
                        newPos.setY(y - 1);
                        break;
                    default:
                        newPos.setX(x);
                        newPos.setY(y);
                        System.out.println("Le pion ne peut pas sortir du tableau !");
                }
        }*/
        if (team == Team.WHITE){
            if( pos2==1 && (x-1)>=0 ){
                newPos.setX(x-1);
                newPos.setY(y+1);
            }
            else if (pos2==2 && (x+1)<=9){
                newPos.setX(x+1);
                newPos.setY(y+1);
            }
            else {newPos.setX(x);newPos.setY(y);System.out.println("Le pion ne peut pas sortir du tableau !");}
        }
        else if (team == Team.BLACK){
            if (pos2==1 && (x-1)>=0){
                newPos.setX(x-1);
                newPos.setY(y-1);
            }
            else if (pos2==2 && (x+1)<=9){
                newPos.setX(x+1);
                newPos.setY(y-1);
            }
            else {newPos.setX(x);newPos.setY(y);System.out.println("Le pion ne peut pas sortir du tableau !");}
        }

        return newPos;
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

