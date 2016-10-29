package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import cardsPackage.BaseCard;
import cardsPackage.TrumpCard;
import gamePackage.Game;
import gamePackage.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class BottomPanelGame extends JPanel {

    Controller controller;

    public BottomPanelGame(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        this.add(new BottomPanelTopLeft(controller), BorderLayout.LINE_START);
        this.add(new BottomPanelTopRight(controller), BorderLayout.CENTER);

        this.setVisible(true);
    }

    /*
    public BottomPanelGame(String label){
        this();
        JLabel jLabel = new JLabel("label");
        this.add(jLabel);
    }


    public BottomPanelGame(Player player, TrumpCard currentTrump, BaseCard lastPlayedCard) {
        this();
        //try {
        System.out.println("using player : " + player.getName());
            this.add(new BottomPanelTopLeft(player.getHand().get(0)), BorderLayout.LINE_START);
            //this.add(new BottomPanelTopRight(lastPlayedCard, currentTrump), BorderLayout.CENTER);
            //this.add(new BottomPanelBottomHandView(player), BorderLayout.PAGE_END);
            this.repaint();
        System.out.println("done");
        //} catch (Exception e) {
        //    System.out.println("Beer Time? " + e);
        //}
    }

    */


    public static void main(String[] args) {


        BottomPanelGame bottomPanel = new BottomPanelGame(new Controller(new Game()));
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
