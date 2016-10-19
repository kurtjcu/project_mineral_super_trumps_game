package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

/*
 * GridBagLayoutDemo.java requires no other files.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class FrameGridBag {
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;


    public static int xSize = 1024;
    public static int ySize = 768;

    /*
    public JPanel setTopLeftPanel()
    {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
    }
    */

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }


        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        TopPanel topPanel = new TopPanel();
        topPanel.setBorder(new LineBorder(Color.GREEN, 2));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(topPanel, c);

        BottomPanel bottomPanel = new BottomPanel();
        bottomPanel.setBorder(new LineBorder(Color.GREEN, 2));
        c.fill = GridBagConstraints.HORIZONTAL;

        c.weightx = 0.5;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(bottomPanel, c);
    }



    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}