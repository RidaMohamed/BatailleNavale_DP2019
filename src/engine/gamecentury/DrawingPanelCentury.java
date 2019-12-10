package engine.gamecentury;

import engine.DrawingPanel;
import engine.GameController;
import model.BattleNavalePainter;
import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawingPanelCentury extends DrawingPanel {
    private XXCenturyButton xxCenturyButton;
    private XVCenturyButton xvCenturyButton;
    /**
     * constructeur Il construit les images pour doublebuffering ainsi que le
     * Panel associe. Les images stockent le battleNavalePainter et on demande au panel la
     * mise a jour quand le battleNavalePainter est fini
     *
     * @param Painter
     * @param controller
     */
    public DrawingPanelCentury(BattleNavalePainter Painter, GameController controller) {
        super(Painter, controller);
        this.xvCenturyButton = new XVCenturyButton();
        this.xxCenturyButton = new XXCenturyButton();
        this.addMouseListener(controller);
    }

    @Override
    protected void paintComponent(Graphics g){super.paintComponent(g);}

    @Override
    public void drawGame() {
        try {
            BufferedImage image1 = ImageIO.read(this.getClass().getResourceAsStream("/back.jpg"));
            this.nextImage.getGraphics().drawImage(image1 , 0 , 0 , width , height , null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage xxcent = ImageIO.read(this.getClass().getResourceAsStream("/bouton_xxcentury.png"));
            this.nextImage.getGraphics().drawImage(xxcent , (int) Constants.rect_xxcentury.x, (int) Constants.rect_xxcentury.y, (int)Constants.rect_xxcentury.width,(int) Constants.rect_xxcentury.height , null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage xvcent = ImageIO.read(this.getClass().getResourceAsStream("/bouton_xvcentury.png"));
            this.nextImage.getGraphics().drawImage(xvcent , (int) Constants.rect_xvcentury.x, (int) Constants.rect_xvcentury.y, (int)Constants.rect_xvcentury.width,(int) Constants.rect_xvcentury.height , null);
        } catch (IOException e) {
            e.printStackTrace();
        }




        // inverses les images doublebuffereing
        BufferedImage temp = this.currentImage;
        // l'image a dessiner est celle qu'on a construite
        this.currentImage = this.nextImage;
        // l'ancienne image est videe
        this.nextImage = temp;
        this.nextImage.getGraphics().fillRect(0, 0, this.width, this.height);
        // met a jour l'image a afficher sur le panel
        this.repaint();
        //this.paintComponent(this.nextImage.getGraphics());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}