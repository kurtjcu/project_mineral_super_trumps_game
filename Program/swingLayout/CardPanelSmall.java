package swingLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class CardPanelSmall extends JPanel {


    int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .2));
    int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize * .13));


    public CardPanelSmall(String fileName, String filePrefix) {

        this.setSize(gameWidth, gameHeight );
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePrefix + fileName));

            JLabel picture = new JLabel(new ImageIcon(image.getScaledInstance(gameWidth,
                    gameHeight, Image.SCALE_SMOOTH)));
            this.add(picture);
        } catch (IOException ex) {
            System.out.println(ex);
        }


    }




    public static void main(String[] args) {

        JFrame frame = new JFrame("TestingTopPanelCenterPlayer");
        String fileName = "Slide01.jpg";
        String filePrefix = "Program/swingLayout/images/";
        CardPanelSmall cardPanel = new CardPanelSmall(fileName, filePrefix);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(cardPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

    }
}
