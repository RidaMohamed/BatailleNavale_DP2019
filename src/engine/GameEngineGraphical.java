package engine;

import model.BattleNavalePainter;
import model.BattleNavaleGame;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private BattleNavaleGame game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private BattleNavalePainter gameBattleNavalePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 *
	 * @param game
	 *            game a lancer
	 * @param gameBattleNavalePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *
	 */
	public GameEngineGraphical(BattleNavaleGame game, BattleNavalePainter gameBattleNavalePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gameBattleNavalePainter = gameBattleNavalePainter;
		this.gameController = gameController;
		this.gui = new GraphicalInterface((BattleNavalePainter) this.gameBattleNavalePainter,this.gameController);
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException, RemoteException {

		if (this.game.getClient().getServerGame() != null)
		this.game.setIsFinished(this.game.getClient().getServerGame().isFinished());

	  while (true){
		if (game.isFinished() == -3) {
			this.gui.paintSplash();
			Thread.sleep(2000);
			game.setIsFinished(-2);
		}

		while (game.isFinished() == -2) {
			this.gui.paintMenu();
			Thread.sleep(1000);
		}


		  while (game.isFinished() == -4) {
			  this.gui.paintWaiting(1);
			  Thread.sleep(100);

			  if (this.game.getClient().getServerGame() != null)
				  this.game.setIsFinished(this.game.getClient().getServerGame().isFinished());

		  }




		while (this.game.isFinished() == -1 ) {
			this.gui.paintCentury();
			Thread.sleep(1000);
		}
		while(this.game.isFinished()== -5){
			this.gui.paintInstructions();
		}
		// creation de l'interface graphique
		// boucle de game
		while (this.game.isFinished() >= 0) {
			if (this.game.isFinished() == 0)
				this.gui.paintPositioning(false, "");

			if (this.game.isFinished() == 5)
				this.gui.paintWaiting(2);

			if (game.getMulti() && game.getClient().getServerGame().isFinished() == 1)
				game.setIsFinished(1);

			// affiche le game
			if (this.game.isFinished() == 1)
				this.gui.paintParty(false, "");
			// met en attente
			Thread.sleep(100);
		}

		this.gui.paintResult();
		Thread.sleep(3000);

	}
	}

}
