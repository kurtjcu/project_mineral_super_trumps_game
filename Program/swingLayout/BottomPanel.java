package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BottomPanel extends JPanel {

    public BottomPanel() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBag.ySize * .666));
        int gameWidth = (int) (Math.round(FrameGridBag.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);
    }

    public static void main(String[] args) {

        BottomPanel bottomPanel = new BottomPanel();
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
