package model;

import java.awt.event.KeyEvent;

import engine.GameController;
import global.Clicks;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * controleur de type KeyListener
 * 
 */
public class BattleNavaleController implements GameController {

	/**
	 * commande en cours
	 */
	private Clicks commandeEnCours;

	/**
	 * construction du controleur par defaut le controleur n'a pas de commande
	 */
	public BattleNavaleController() {
		this.commandeEnCours = Clicks.IDLE;
	}

	/**
	 * quand on demande les commandes, le controleur retourne la commande en
	 * cours
	 * 
	 * @return commande faite par le joueur
	 */
	public Clicks getClicks() {
		return this.commandeEnCours;
	}

	@Override
	/**
	 * met a jour les commandes en fonctions des touches appuyees
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyChar()) {
		// si on appuie sur 'q',commande joueur est gauche
		case 'l':
		case 'L':
			this.commandeEnCours = Clicks.IDLE;
			break;
		}
	}

	@Override
	/**
	 * met a jour les commandes quand le joueur relache une touche
	 */
	public void keyReleased(KeyEvent e) {
		this.commandeEnCours = Clicks.IDLE;
	}

	@Override
	/**
	 * ne fait rien
	 */
	public void keyTyped(KeyEvent e) {

	}

}