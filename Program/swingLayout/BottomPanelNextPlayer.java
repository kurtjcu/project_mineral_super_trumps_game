package swingLayout; /**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BottomPanelNextPlayer extends JPanel {


    JLabel playerWhosTurnItIs = new JLabel("none", SwingConstants.CENTER);
    JButton ready = new JButton("Ready");
    Controller controller;

    public BottomPanelNextPlayer(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;

        int gameHeight = (int) (Math.round(Frame.ySize * .666));
        int gameWidth = (int) (Math.round(Frame.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        playerWhosTurnItIs.setFont(Frame.largeFont);
        playerWhosTurnItIs.setBorder(new EmptyBorder(30,30,30,30));
        ready.setFont(Frame.largeFont);
        ready.addActionListener(controller.showPlayerGame);
        this.add(playerWhosTurnItIs, BorderLayout.PAGE_START);
        this.add(ready, BorderLayout.CENTER);
        String prefix = "It is currently ";
        String suffix = "'s turn ";
        playerWhosTurnItIs.setText(prefix + controller.game.currentPlayer.getName() + suffix);
        this.setVisible(true);
    }


    /*
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
    */
}
