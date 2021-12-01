package fr.death.ynov;

import java.util.Scanner;

public class Game {

    /*

    TODO: Manger les pions adverses possible mais pas de condition de victoire
    TODO: Faire du 1 joueur --> code boucle sur pions "IA" et check si mouvement possible

    TODO:   Prendre des pions et aller une case après si prise et si case vide
    TODO: Condtion de victoire --> check l'array list de la team, si vide alors défaite
     */


    public void menu() {
        System.out.println(Menu.Bonjour);
        do {
            System.out.println(Menu.MenuChoix);
            Scanner sc = new Scanner(System.in);
            int i = 0;
            try {
                i = sc.nextInt();
            } catch (Exception e) {
                System.out.println(e);
            }
            switch (i) {
                case 1:
                    System.out.println("Not working");
                    break;
                case 2:
                    System.out.println("Début de la partie :");
                    start();
                    break;
                case 3:
                    System.out.println(Menu.Regle);
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    System.exit(0);
                default:
                    System.out.println("Le choix n'est pas bon , veuillez réessayer");
            }
        }while (true);
    }

    private void start() {
        final Map map = new Map();
        map.reset();
        final Player player1 = new Player(Team.WHITE);
        final Player player2 = new Player(Team.BLACK);
        final Player player3 = new Player(Team.NULL);
        map.spawnTeam(player1, Map.StartPosition.UP);
        map.spawnTeam(player2, Map.StartPosition.DOWN);
        displayMap(map);
        int turn = 0;
        Pawn PawnTest=new Pawn(player3,map);
        Pawn PawnPlayer1=new Pawn(player1,map);
        Pawn PawnPlayer2=new Pawn(player2,map);
        while (!player1.isDeath() || !player2.isDeath()) {
            Team testTurn=teamTurn(map,player1,player2,turn);
            Vector2D sideStep=new Vector2D(0,0);
            if(testTurn== PawnPlayer1.getOwner().getTeam()){
                sideStep= scannerActualPawnPosition(player1,map);
            }else if(testTurn== PawnPlayer2.getOwner().getTeam()){
                sideStep= scannerActualPawnPosition(player2,map);
            }

                  if(map.getPawn(sideStep).getOwner().getTeam()==testTurn){
                      Vector2D newPosition=scannerNewPawnPosition(testTurn,sideStep.getX(),sideStep.getY(),PawnTest);
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
    }

    private Vector2D scannerActualPawnPosition(Player playerpawn, Map map){
        Vector2D initPos= new Vector2D(0,0);


        System.out.println("veuillez choisir la coordonnée x  du pion que vous souhaitez bouger :");
        Scanner sc = new Scanner(System.in);
        int pos1 = sc.nextInt();
        System.out.println("veuillez choisir la coordonnée y  du pion que vous souhaitez bouger :");
        Scanner sc2 = new Scanner(System.in);
        int pos2= sc2.nextInt();
        initPos.setX(pos1);
        initPos.setY(pos2);
        boolean nonvalid = !playerpawn.getPawnList().contains(map.getPawn(pos1, pos2));
        while(nonvalid){
            //pawn.getOwner()==vide
            System.out.println("Il n'y a pas de pion à cet endroit !");
            System.out.println("veuillez choisir la coordonnée x  du pion que vous souhaitez bouger :");
            sc = new Scanner(System.in);
            pos1 = sc.nextInt();
            System.out.println("veuillez choisir la coordonnée y  du pion que vous souhaitez bouger :");
            sc2 = new Scanner(System.in);
            pos2= sc2.nextInt();
            //Vector2D testVect=new Vector2D(pos1,pos2);
            initPos.setX(pos1);
            initPos.setY(pos2);
            System.out.println(initPos);
            if(playerpawn.getPawnList().contains(map.getPawn(initPos))){
                nonvalid=false;
            }
            System.out.println("OK");
        }
        System.out.println(nonvalid);
        return initPos;
    }
    private boolean Diagonale(Team team,int x, int y){
        boolean result=false;
        Vector2D afterEatW= new Vector2D(x-2,y+1);
        Vector2D afterEatWBis= new Vector2D(x+2,y+1);
        Vector2D afterEatB= new Vector2D(x-2,y-1);
        Vector2D afterEatBBis= new Vector2D(x+2,y-1);
        Vector2D testPos=new Vector2D(x,y);
        if(team == Team.WHITE  && afterEatW.getX()<0 || team == Team.WHITE  && afterEatWBis.getX()>9) {
            result=true;
        }
        else if(team==Team.BLACK && afterEatB.getX()<0 || team == Team.BLACK  && afterEatBBis.getX()<0){
            result=true;
        }
        return result;
        }

    private Vector2D scannerNewPawnPosition(Team team, int x, int y,Pawn pawnTest){
        Vector2D testPos= new Vector2D(x,y);
        Vector2D newPos= new Vector2D(0,0);
        System.out.println("Veuillez choisir vos mouvements de pions :" +
                    "\n1 - Diagonale Gauche" +
                    "\n2 - Diagonale Droite");
        Scanner sc = new Scanner(System.in);
        int pos2 = sc.nextInt();
        int testX= x+1;
        int testNeg= x-1;
        Vector2D toEatPawn=new Vector2D(testX,y);
        Vector2D toEatPawnNext=new Vector2D(testNeg,y);


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
        boolean emptyCase= false;
        if (team == Team.WHITE){
            //boucle if case prise et derriere libre --> manger le pion (au moins passer dessus)

             if( pos2==1 && (x-1)>=0){
                 pawnTest.setPosition(toEatPawnNext);
                 if(pawnTest.getOwner().getTeam()==Team.NULL){
                     newPos.setX(x-1);
                     newPos.setY(y+1);
                    // System.out.println("OK");
                 }
            } else if (pos2==2 && (x+1)<=9) {
                 pawnTest.setPosition(toEatPawn);
                 if(pawnTest.getOwner().getTeam()==Team.NULL){
                     newPos.setX(x + 1);
                     newPos.setY(y + 1);
                     //System.out.println("OK 2");
                 }
             }
            else {newPos.setX(x);newPos.setY(y);System.out.println("Le pion ne peut pas sortir du tableau !");}
        }
        else if (team == Team.BLACK){
            if (pos2==1 && (x-1)>=0 ){
                pawnTest.setPosition(toEatPawnNext);
                if(pawnTest.getOwner().getTeam()==Team.NULL){
                    newPos.setX(x-1);
                    newPos.setY(y-1);
                   // System.out.println("OK ");
                }
            }
            else if (pos2==2 && (x+1)<=9){
                pawnTest.setPosition(toEatPawnNext);
                if(pawnTest.getOwner().getTeam()==Team.NULL){
                    newPos.setX(x+1);
                    newPos.setY(y-1);
                   // System.out.println("OK 2");
                }
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

