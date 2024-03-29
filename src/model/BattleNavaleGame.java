package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Game;
import model.century_factory.BoatTimeFactory;
import model.century_factory.boats.Boat;
import model.global.Constants;
import model.global.Turn;
import model.player.HumanPlayer;
import model.player.MachinePlayer;
import model.player.strategy.MachineAttackRandom;
import model.player.strategy.MachineCrossAttack;
import save.FileManager;
import model.player.strategy.MachineAttackRandom;



public class BattleNavaleGame implements Game {

	private HumanPlayer humanPlayer;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;
	private FileManager fileManager;
	private int isFinished;
    private int boatshealth;

	/**
	 * Simple Constructor
	 * To load the game
	 * @param source
	 */
	public BattleNavaleGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;

			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		humanPlayer   = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineCrossAttack(this));
		fileManager   = new FileManager(this);
		turn = Turn.PlayerTurn;
	}

	/**
	 * Simple Constructor
	 */
	public BattleNavaleGame() {
		isFinished = -3;
	}

	/**
	 * Initialize the game
	 */
	public void initialize(){
		humanPlayer = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineCrossAttack(this));
		fileManager = new FileManager(this);
		turn = Turn.PlayerTurn;
	}
	public void inst(){

	}

	/**
	 * This methode call the boatTimeFactory to create
	 * boat foer each humaine and machine player
	 */
	public void createBoats(){
		//getting humaine and machine borad to add the created boat
		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			humanPlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
        boatshealth = humanPlayer.getBoard().boatsHealth();
	}

	/**
	 * change boat position using mouse
	 */
	public void moveBoats() {
		for(Boat b : humanPlayer.getBoard().getBoats()){
			humanPlayer.getBoard().setBoatPosition(b);
		}
	}

	public void setCentury(BoatTimeFactory timeFactory){
		this.boatTimeFactory = timeFactory;
	}

	public void setIsFinished(int isFinished) { this.isFinished = isFinished; }

	public void setTurn(Turn turn) { this.turn = turn; }

	public void setHumanPlayer(HumanPlayer humanPlayer) {
		this.humanPlayer = humanPlayer;
	}

	public void setMachinePlayer(MachinePlayer machinePlayer) {
		this.machinePlayer = machinePlayer;
	}

	public void setStrategy(MachinePlayer machinePlayer) {
		this.machinePlayer = machinePlayer;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public Turn getTurn() {
		return turn;
	}

	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}

	public MachinePlayer getMachinePlayer() {
		return machinePlayer;
	}

    public int isFinished() {
        if (isOver() != null) {
            isFinished = 2;
        }
        return isFinished;
    }

	@Override
	public void draw(BufferedImage img) throws InterruptedException {
	}

    public String isOver(){
        String over = null;
        if(isFinished >0) {
            if (humanPlayer.getScoreHits() == boatshealth) {
                over = "win";
            }else if (machinePlayer.getScoreHits() == boatshealth) {
                over = "lose";
            }
        }
        return over;
    }
}
