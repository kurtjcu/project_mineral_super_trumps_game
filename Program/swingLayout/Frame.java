package swingLayout;

import gamePackage.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class Frame extends JFrame {

    public static int xSize = 1024;
    public static int ySize = 768;

    public static Font largeFont = new Font("SansSerif", Font.BOLD, 20);
    public static Font extraLargeFont = new Font("SansSerif", Font.BOLD, 40);

    public static String filePrefix = "Program/swingLayout/images/";

    public Container pane;

    //main frames
    public TopPanel topPanel;
    public BottomPanel bottomPanel;

    //Top Frames
    public TopPanelLeft topPanelLeft;
    public TopPanelCenter topPanelCenter;
    public TopPanelRight topPanelRight;

    //Bottom Frames

    public BottomPanelNextPlayer bottomPanelNextPlayer;
    public BottomPanelGame bottomPanelGame;
    public BottomPanelFinishedGame bottomPanelFinishedGame;
    public BottomPanelFinishedHand bottomPanelFinishedHand;

    public Frame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(xSize, ySize);
        pane = this.getContentPane();
        pane.setLayout(new BorderLayout());

        topPanel = new TopPanel();
        bottomPanel = new BottomPanel();

        topPanelLeft = new TopPanelLeft();
        topPanelCenter = new TopPanelCenter();
        topPanelRight = new TopPanelRight();


        //setup top panel
        pane.add(topPanel, BorderLayout.PAGE_START);
        topPanel.add(topPanelLeft, BorderLayout.LINE_START);
        topPanel.add(topPanelCenter, BorderLayout.CENTER);
        topPanel.add(topPanelRight, BorderLayout.LINE_END);

        // add bottom panel add panels to bottom panel depending on game
        pane.add(bottomPanel, BorderLayout.CENTER);

        this.pack();
        this.setVisible(true);
    }

    void topPanelRedraw(){
        topPanel.removeAll();
        topPanel.add(topPanelLeft, BorderLayout.LINE_START);
        topPanel.add(topPanelCenter, BorderLayout.CENTER);
        topPanel.add(topPanelRight, BorderLayout.LINE_END);
    }

    public void initSetPlayerInfo(){
        topPanelLeft.setBottomPanel(Game.currentPlayer.getName());
        topPanelCenter.setPlayers(Game.getPlayers());
        topPanelRedraw();
    }


    public void showNextPlayer(String playerName){
        bottomPanel.removeAll();
        bottomPanelNextPlayer = new BottomPanelNextPlayer(playerName);
        bottomPanel.add(bottomPanelNextPlayer, BorderLayout.CENTER);
    }


    //TODO: add player details and make sure it passes them down the line
    public void showPlayerGameHand(){
        bottomPanel.removeAll();
        showNextPlayer("cunt");
        bottomPanel.invalidate();
        //bottomPanel.add(new BottomPanelGame(Controller.game.currentPlayer, Game.currentTrump, Game.lastPlayedCard));

        System.out.println("finished adding");
    }

    //TODO: add player details and make sure it passes them down the line
    public void showPlayerFinishedHand(){
        bottomPanel.removeAll();
        bottomPanel.add(new BottomPanelFinishedHand(), BorderLayout.CENTER);
    }

    //TODO: add player details and make sure it passes them down the line
    public void showFinishedGame(){
        bottomPanel.removeAll();
        bottomPanel.add(new BottomPanelFinishedGame());
    }



    public static void main(String[] args) {
        Frame frame = new Frame();
    }
}
