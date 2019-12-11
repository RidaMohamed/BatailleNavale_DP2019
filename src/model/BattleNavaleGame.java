package model;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import engine.Game;
import model.century_factory.BoatFactoryXVCentury;
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



public class BattleNavaleGame extends UnicastRemoteObject implements Game {

	private HumanPlayer humanPlayer;
	private MachinePlayer machinePlayer;
	private Turn turn;
	private BoatTimeFactory boatTimeFactory;
	private FileManager fileManager;
	private int isFinished;

	/**
	 * Simple Constructor
	 * To load the game
	 * @param source
	 */
	public BattleNavaleGame(String source)throws RemoteException {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
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


	public BattleNavaleGame()throws RemoteException {
		initialize();
	}


	public void initialize()throws RemoteException {
		humanPlayer = new HumanPlayer(this);
		machinePlayer = new MachinePlayer(this);
		machinePlayer.setStrategy( new MachineCrossAttack(this));
		boatTimeFactory = new BoatFactoryXVCentury();
		fileManager = new FileManager(this);
		isFinished = -3;
		turn = Turn.PlayerTurn;

	}

	/**
	 *
	 */
	public void createBoats()throws RemoteException {
		for (int i = 0 ; i<Constants.boat_length_size.length  ; i++){
			humanPlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
			machinePlayer.getBoard().addBoat(boatTimeFactory.createBoat(Constants.boat_length_size[i]));
		}
		fileManager.save();
	}

	/**
	 *
	 */
	public void moveBoats() {
		for(Boat b : humanPlayer.getBoard().getBoats()){
			humanPlayer.getBoard().setBoatPosition(b);
		}
	}

	public void setCentury(BoatTimeFactory timeFactory){
		this.boatTimeFactory = timeFactory;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFactory(BoatTimeFactory timeFactory){
		this.boatTimeFactory = timeFactory;
	}

	public void setHumanPlayer(HumanPlayer humanPlayer) {
		this.humanPlayer = humanPlayer;
	}

	public void setMachinePlayer(MachinePlayer machinePlayer) {
		this.machinePlayer = machinePlayer;
	}

	public void setStrategy(MachinePlayer machinePlayer) {
		this.machinePlayer = machinePlayer;
	}

	public void setTurn(Turn turn) {
		this.turn = turn;
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
		return isFinished;
	}

	@Override
	public void draw(BufferedImage img) throws InterruptedException {

	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}
}
