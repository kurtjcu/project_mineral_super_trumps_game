package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

//import cardsPackage.BaseCard;
//import cardsPackage.TrumpCard;
//import gamePackage.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.Frame;
import java.util.ArrayList;

public class BottomPanelGame extends JPanel {



    public BottomPanelGame() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        this.setVisible(true);
    }

    // TODO: add this functionality back to project when attached to game
    /*
    public BottomPanelGame(Player player, TrumpCard currentTrump, BaseCard lastPlayedCard) {
        this();
        try {
            this.add(new BottomPanelTopLeft(player.getHand().get(0)), BorderLayout.LINE_START);
            this.add(new BottomPanelTopRight(lastPlayedCard, currentTrump), BorderLayout.CENTER);
            this.add(new BottomPanelBottomHandView(player), BorderLayout.PAGE_END);
            this.repaint();
        } catch (Exception e) {
            System.out.println("Beer Time? " + e);
        }
    }
    */


    public static void main(String[] args) {

        ArrayList<String> names = new ArrayList<>();
        for(int i = 0; i <= 5; i++){
            names.add("Player " + (1 + i));
        }
        BottomPanelGame bottomPanel = new BottomPanelGame();
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
