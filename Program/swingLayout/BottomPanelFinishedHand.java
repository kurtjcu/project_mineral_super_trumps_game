package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BottomPanelFinishedHand extends JPanel {


    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);
    JPanel text = new JPanel(new BorderLayout());
    JButton ready = new JButton("Awesome");

    public BottomPanelFinishedHand() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .666));
        int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        setText();
        ready.setFont(largeFont);
        this.add(text, BorderLayout.PAGE_START);
        this.add(ready, BorderLayout.CENTER);
        this.setVisible(true);
    }



    void setText(){
        JLabel congrats = new JLabel("Congratulations!", SwingConstants.CENTER);
        JLabel empyHand = new JLabel("You have emptied your hand", SwingConstants.CENTER);
        JLabel pleaseWait = new JLabel("Please wait until you have emptied your hand", SwingConstants.CENTER);
        congrats.setFont(largeFont);
        empyHand.setFont(largeFont);
        pleaseWait.setFont(largeFont);
        congrats.setBorder(new EmptyBorder(30,30,30,30));
        empyHand.setBorder(new EmptyBorder(30,30,30,30));
        pleaseWait.setBorder(new EmptyBorder(30,30,30,30));
        text.setBorder(new EmptyBorder(30,30,30,30));
        text.add(congrats, BorderLayout.PAGE_START);
        text.add(empyHand, BorderLayout.CENTER);
        text.add(pleaseWait, BorderLayout.PAGE_END);
        text.setVisible(true);
    }



    public static void main(String[] args) {

        BottomPanelFinishedHand bottomPanel = new BottomPanelFinishedHand();
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
