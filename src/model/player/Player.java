package model.player;

import engine.Game;
import model.board.Board;
import model.BattleNavaleGame;
import model.global.Constants;

import java.io.Serializable;
import java.rmi.RemoteException;

public class Player implements Serializable {

    protected Game game;
    protected Board board;
    protected int pv;

    public Player(Game game) {
        this.game = game;
        this.board = new Board();
        this.pv = Constants.boat_length_size.length;
    }

    /**
     * getting the borad which contains
     * the machine model.player boats positions
     */
    public Board getBoard(){
         return this.board;
    }

    /**
     * getting the Battle Navale Game class
     * player know the Battle Navale Game class
     * @return
     */
    public Game getGame() {
        return game;
    }


    /**
     * Methode to get data to save the game party
     * @return
     */
    public StringBuilder getData(){
        StringBuilder str = new StringBuilder();
        str.append("/");
        return str;
    }

    /**
     * Methode to get data to save the game party
     * @return
     */
    public void attack() throws RemoteException {
    }

    public int getPv() {
        return pv;
    }

    /**
     * methode de decrease the pv of player
     * which represente the live on game for each player
     */
    public void subPv(){
        this.pv--;
        //if (this.pv == 0)
            //this.getGame().setIsFinished(-2);
    }
}
