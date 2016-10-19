package swingLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelRight extends JPanel {

    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBag.ySize * .333));
    int gameWidth = (int) (Math.round(FrameGridBag.xSize * .333));

    TopPanelRightFinishedPlayers finishedPlayersPanel = new TopPanelRightFinishedPlayers();
    JButton showRulesButton = new JButton("Show Rules");

    public TopPanelRight() {
        super(new FlowLayout());

        this.add(finishedPlayersPanel);
        this.add(showRulesButton);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }


    public static void main(String[] args) {

        TopPanelRight topPanelCenter = new TopPanelRight();
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
