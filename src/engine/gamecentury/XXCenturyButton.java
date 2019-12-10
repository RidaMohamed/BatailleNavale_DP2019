package engine.gamecentury;

import model.global.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class XXCenturyButton {
    private BufferedImage ok;

    public XXCenturyButton(){
        try {
            ok = ImageIO.read(getClass().getClassLoader().getResourceAsStream("pos.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(ok, (int) Constants.rect_ok.x, (int)Constants.rect_ok.y,
                (int) Constants.rect_ok.width, (int)Constants.rect_ok.height, null);

    }
}