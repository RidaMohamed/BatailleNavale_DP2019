package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 */
import engine.painter.Painter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * la clase chargee de Dessiner
	 */
	protected Painter painter;

	protected GameController controller;

	/**
	 * image suivante est l'image cachee sur laquelle dessiner
	 */
	protected BufferedImage nextImage;

	/**
	 * image en cours est l'image entrain d'etre affichee
	 */
	protected BufferedImage currentImage;

	/**
	 * la taille des images
	 */
	protected int width, height;



	/**
	 * constructeur Il construit les images pour doublebuffering ainsi que le
	 * Panel associe. Les images stockent le painter et on demande au panel la
	 * mise a jour quand le painter est fini
	 * @param painter
	 * @param controller
	 */
	public DrawingPanel(Painter painter, GameController controller) {
		super();
		this.width = painter.getScreenWidth();
		this.height = painter.getScreenHeight();
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.painter=painter;
		this.controller = controller;

		// cree l'image buffer et son graphics
		this.nextImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		this.currentImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * demande de mettre a jour le rendu de l'image sur le Panel. Creer une
	 * nouvelle image vide sur laquelle dessiner
	 */
	public void drawGame() {
		// generer la nouvelle image
		this.painter.draw(this.nextImage);

		// inverses les images doublebuffereing
		BufferedImage temp = this.currentImage;
		// l'image a dessiner est celle qu'on a construite
		this.currentImage = this.nextImage;
		// l'ancienne image est videe
		this.nextImage = temp;
		this.nextImage.getGraphics()
				.fillRect(0, 0, this.width, this.height);
		// met a jour l'image a afficher sur le panel
		this.repaint();
	}

	/**
	 * redefinit la methode paint consiste a dessiner l'image en cours
	 * 
	 * @param g
	 *            graphics pour dessiner
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(this.currentImage, 0, 0, getWidth(), getHeight(), 0, 0,
				getWidth(), getHeight(), null);
	}

}
