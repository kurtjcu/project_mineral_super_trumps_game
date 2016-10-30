package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import gamePackage.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BottomPanelFinishedHand extends JPanel {

    JPanel text = new JPanel(new BorderLayout());
    JButton ready = new JButton("Awesome");

    Controller controller;

    public BottomPanelFinishedHand(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        setText();
        ready.setFont(Frame.largeFont);
        ready.addActionListener(controller.awesomePlayerEmptiedHand);
        this.add(text, BorderLayout.PAGE_START);
        this.add(ready, BorderLayout.CENTER);
        this.setVisible(true);
    }


    void setText(){
        JLabel congrats = new JLabel("Congratulations!", SwingConstants.CENTER);
        JLabel emptyHand = new JLabel("You have emptied your hand", SwingConstants.CENTER);
        JLabel pleaseWait = new JLabel("Please wait until the game has finished", SwingConstants.CENTER);
        congrats.setFont(Frame.largeFont);
        emptyHand.setFont(Frame.largeFont);
        pleaseWait.setFont(Frame.largeFont);
        congrats.setBorder(new EmptyBorder(30,30,30,30));
        emptyHand.setBorder(new EmptyBorder(30,30,30,30));
        pleaseWait.setBorder(new EmptyBorder(30,30,30,30));
        text.setBorder(new EmptyBorder(30,30,30,30));
        text.add(congrats, BorderLayout.PAGE_START);
        text.add(emptyHand, BorderLayout.CENTER);
        text.add(pleaseWait, BorderLayout.PAGE_END);
        text.setVisible(true);
    }



    public static void main(String[] args) {

        BottomPanelFinishedHand bottomPanel = new BottomPanelFinishedHand(new Controller(new Game()));
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
