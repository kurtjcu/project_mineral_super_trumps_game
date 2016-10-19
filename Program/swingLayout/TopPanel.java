package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TopPanel extends JPanel {

    TopPanelLeft leftPanel = new TopPanelLeft();
    TopPanelCenter centerPanel = new TopPanelCenter();
    TopPanelRight rightPanel = new TopPanelRight();

    public TopPanel() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBag.ySize * .333));
        int gameWidth = (Math.round(FrameGridBag.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        displayThreeHorizontalFrames(leftPanel, centerPanel, rightPanel);
        this.setVisible(true);
    }

    public void displayCenterText(String textToDisplay){
        this.removeAll();
        JLabel label = new JLabel(textToDisplay, SwingConstants.CENTER);
        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        label.setFont(font1);
        this.add(label);
    }

    public void displayThreeHorizontalFrames(JPanel frame1, JPanel frame2 ,JPanel frame3) {
        this.removeAll();
        this.setLayout(new BorderLayout());

        this.add(frame1, BorderLayout.LINE_START);
        this.add(frame2, BorderLayout.CENTER);
        this.add(frame3, BorderLayout.LINE_END);
;
    }

    public static void main(String[] args) {

        TopPanel topPanel = new TopPanel();
        topPanel.setBorder(new LineBorder(Color.GREEN, 2));


        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(topPanel);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);


    }
}
