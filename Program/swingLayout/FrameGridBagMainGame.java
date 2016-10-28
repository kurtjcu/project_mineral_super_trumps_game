package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import gamePackage.Game;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class FrameGridBagMainGame {
    final static boolean RIGHT_TO_LEFT = false;

    public static int xSize = 1024;
    public static int ySize = 768;


    public TopPanel topPanel;
    public BottomPanel bottomPanel;
    static JFrame frame = new JFrame("GridBagLayoutDemo");

    GridBagConstraints cTopPanel = new GridBagConstraints();
    GridBagConstraints cBottomPanel = new GridBagConstraints();
    Container pane;

    public void addComponentsToPane(Container pane, Game game) {

        this.pane = pane;
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        pane.setLayout(new GridBagLayout());

        topPanel = new TopPanel(game);
        topPanel.setBorder(new LineBorder(Color.GREEN, 2));
        cTopPanel.fill = GridBagConstraints.HORIZONTAL;
        cTopPanel.weightx = 0.5;
        cTopPanel.gridwidth = 3;
        cTopPanel.gridx = 0;
        cTopPanel.gridy = 0;
        pane.add(topPanel, cTopPanel);

        bottomPanel = new BottomPanel();
        bottomPanel.showNextPlayer(GuiView.game.currentPlayer.getName());
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2));
        cBottomPanel.fill = GridBagConstraints.HORIZONTAL;

        cBottomPanel.weightx = 0.5;
        cBottomPanel.gridwidth = 3;
        cBottomPanel.gridx = 0;
        cBottomPanel.gridy = 1;
        pane.add(bottomPanel, cBottomPanel);
    }

    public void redrawBottomFrameGame(){
        bottomPanel = new BottomPanel();
        bottomPanel.showGame();
        pane.add(bottomPanel, cBottomPanel);
        frame.pack();
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI(Game game) {
        //Create and set up the window.

        FrameGridBagMainGame mainFrame = new FrameGridBagMainGame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addComponentsToPane(frame.getContentPane(), game);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(new Game());
            }
        });
    }
}