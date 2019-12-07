package model.player.strategy;

import model.BattleNavaleGame;
import model.board.Board;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Orientation;
import model.global.Position;
import model.global.Turn;
import model.player.MachinePlayer;

import java.util.ArrayList;
import java.util.List;

public class MachineCrossAttack implements StrategyMahcineAttack {
    private BattleNavaleGame battleNavaleGame;
    private Position x1,x2,x3,x4;
    private boolean randomIsOut=true;
    private int chosenPos=1;

    public MachineCrossAttack(BattleNavaleGame battleNavaleGame) {
        this.battleNavaleGame = battleNavaleGame;
    }

    @Override
    public void attack(MachinePlayer machinePlayer) {

        boolean b;
        Board board;
        int randX=0, randY=0;
        board = battleNavaleGame.getHumanPlayer().getBoard();

        do {
            if(randomIsOut) {
                randX = (int) (Math.random() * (Constants.WIDTH) + 1);
                randY = (int) (Math.random() * (Constants.HEIGHT) + 1);
                // Generating a random
                //System.out.println("la machine a choisi " + randX + "  "+randY);
                //get borad of humain model.player
                //Verfie is the postions is ok to attack
                b = board.isPosFree(randX, randY);
                System.out.println(randomIsOut);
            }else{
                switch(chosenPos){
                    case 1:
                        if (x1.getX() <= Constants.WIDTH) {
                            randX = x1.getX();
                            randY = x1.getY();
                            break;
                        }
                    case 2 :
                        if (x2.getY() <= Constants.HEIGHT) {
                            randX = x2.getX();
                            randY = x2.getY();
                            break;
                        }
                    case 3:
                        if (x3.getX() >= 1) {
                            randX = x3.getX();
                            randY = x3.getY();
                            break;
                        }
                    case 4 :
                        if (x4.getY() >= 1) {
                            randX = x4.getX();
                            randY = x4.getY();
                            break;
                        }
                }
                b = board.isPosFree(randX, randY);
                if (!b){
                    if (this.chosenPos == 4){
                        this.chosenPos=1;
                        randomIsOut=true;
                    }else {
                        chosenPos++;
                    }
                }
            }
        } while (!b);

        boolean missedShot = true;
        List<Boat> boats = battleNavaleGame.getHumanPlayer().getGame().
                getHumanPlayer().getBoard().getBoats();
        for (Boat boat : boats) {
            //do {
            if (boat.isOnCase(randX, randY)) {
                System.out.println(randX + " / " + randY);

                randomIsOut=false;
                chosenPos = 1;
                //adding the hited pos to the borad shoot list
                board.addPosAttacked(new Position(randX, randY), true);
                boat.boatIsHit(randX, randY);
                missedShot = false;

                if(randX+1<=Constants.WIDTH+1){
                    x1=new Position(randX+1,randY);
                    System.out.println(x1.getX() + " /// " + x1.getY());
                }
                if(randY+1<=Constants.HEIGHT+1){
                    x2=new Position(randX,randY+1);
                    System.out.println(x2.getX() + " /// " + x2.getY());
                }
                if (randX-1>=1){
                    x3=new Position(randX-1,randY);
                    System.out.println(x3.getX() + " /// " + x3.getY());
                }
                if (randY-1>=1){
                    x4=new Position(randX,randY-1);
                    System.out.println(x4.getX() + " /// " + x4.getY());
                }

                if (boat.isDistruct()) {
                    this.battleNavaleGame.getHumanPlayer().subPv();
                    ArrayList<Position> positions = boat.getCases();
                    for (int k = 0; k < positions.size(); k++) {
                        board.addPosAttacked(positions.get(k), true);
                    }
                    boat.deletePositions();
                    //board.deleteBoat(boat);
                }
                //}while(!boat.isDistruct());
            }
        }

        if (missedShot){
            board.addPosAttacked(new Position(randX, randY), false);
            if(!randomIsOut){
                chosenPos++;
                if(chosenPos==5){
                    randomIsOut=true;
                    chosenPos=1;
                }
            }

        }

        battleNavaleGame.getHumanPlayer().getGame().setTurn(Turn.PlayerTurn);

    }
}
