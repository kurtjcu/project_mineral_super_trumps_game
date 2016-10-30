package swingLayout;//import gamePackage.Player;

import gamePackage.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelRight extends JPanel {


    int gameHeight = (int) (Math.round(Frame.ySize * .333));
    int gameWidth = (int) (Math.round(Frame.xSize * .333));
    Controller controller;


    TopPanelRightFinishedPlayers finishedPlayersPanel;
    JButton showRulesButton;

    public TopPanelRight(Controller controller) {
        super(new FlowLayout());
        this.controller = controller;

        finishedPlayersPanel = new TopPanelRightFinishedPlayers(controller);
        showRulesButton = new JButton("Show Rules");

        this.add(finishedPlayersPanel);
        this.add(showRulesButton);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }

    /*
    public void setFinishedPlayers(ArrayList<Player> players){
        finishedPlayersPanel.setFinishedPlayers(players);
    }
    */

    public static void main(String[] args) {

        TopPanelRight topPanelCenter = new TopPanelRight(new Controller(new Game()));
        topPanelCenter.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(topPanelCenter);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



        frame.pack();

    }
}
