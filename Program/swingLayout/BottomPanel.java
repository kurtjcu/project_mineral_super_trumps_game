package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import gamePackage.*;

public class BottomPanel extends JPanel {

    public BottomPanel() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .666));
        int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);
    }

    void showNextPlayer(String playerName ){
        this.setLayout(new BorderLayout());
        this.add(new BottomPanelNextPlayer(playerName));
    }

    void showFinishedHand(){
        this.setLayout(new BorderLayout());
        this.add(new BottomPanelFinishedHand());
    }

    void showEndOfGame(ArrayList<String> players){
        this.setLayout(new BorderLayout());
        this.add(new BottomPanelFinishedGame(players));
    }

    void showGame(Player player  ){
        this.setLayout(new BorderLayout());
        this.add(new BottomPanelGame());
    }

    public static void main(String[] args) {

        BottomPanel bottomPanel = new BottomPanel();
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2));
        //bottomPanel.showNextPlayer("jim bob");
        //bottomPanel.showFinishedHand();

        ArrayList<String> players = new ArrayList<>();
        players.add("winner");
        players.add("2nd");
        players.add("3rd");
        players.add("4th");
        players.add("Looza!");
        bottomPanel.showEndOfGame(players);
        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(bottomPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
