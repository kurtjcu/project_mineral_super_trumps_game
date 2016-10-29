package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import cardsPackage.BaseCard;
import cardsPackage.TrumpCard;
import gamePackage.Game;
import gamePackage.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanelNextPlayer extends JPanel implements ActionListener {


    JLabel playerWhosTurnItIs = new JLabel("none", SwingConstants.CENTER);
    JButton ready = new JButton("Ready");

    public BottomPanelNextPlayer() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        playerWhosTurnItIs.setFont(Frame.largeFont);
        playerWhosTurnItIs.setBorder(new EmptyBorder(30,30,30,30));
        ready.setFont(Frame.largeFont);
        ready.addActionListener(this);
        this.add(playerWhosTurnItIs, BorderLayout.PAGE_START);
        this.add(ready, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public BottomPanelNextPlayer(String playerName) {
        this();
        String prefix = "It is currently ";
        String suffix = "'s turn ";
        playerWhosTurnItIs.setText(prefix + playerName + suffix);

    }


    public void actionPerformed(ActionEvent e) {
        System.out.println("drink beer");
        controller.frame.showNextPlayer("fuckhead");
    }


    public static void main(String[] args) {

        BottomPanelNextPlayer bottomPanel = new BottomPanelNextPlayer("JimBob");
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
