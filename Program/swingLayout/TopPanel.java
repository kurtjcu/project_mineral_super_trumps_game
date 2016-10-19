package swingLayout;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TopPanel extends JPanel {

    public TopPanel() {
        super(new BorderLayout());
        int gameHeight = (int) (Math.round(FrameGridBag.ySize * .333));
        int gameWidth = (Math.round(FrameGridBag.xSize));
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
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
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        topPanel.displayCenterText("Free Beer");

        String textToDisplay = "FreeBeer";
        JPanel testPanel1 = new JPanel(new BorderLayout());
        JPanel testPanel2 = new JPanel(new BorderLayout());
        JPanel testPanel3 = new JPanel(new BorderLayout());

        Font font1 = new Font("SansSerif", Font.BOLD, 20);
        JLabel label1 = new JLabel(textToDisplay + 1, SwingConstants.CENTER);
        label1.setFont(font1);

        JLabel label2 = new JLabel(textToDisplay + 2, SwingConstants.CENTER);
        label2.setFont(font1);

        JLabel label3 = new JLabel(textToDisplay + 3, SwingConstants.CENTER);
        label3.setFont(font1);


        testPanel1.setPreferredSize(new Dimension(FrameGridBag.xSize / 3, FrameGridBag.ySize / 3));
        testPanel1.add(label1);
        testPanel1.setBorder(new LineBorder(Color.GREEN, 2));

        testPanel2.setPreferredSize(new Dimension(FrameGridBag.xSize / 3, FrameGridBag.ySize / 3));
        testPanel2.add(label2);
        testPanel2.setBorder(new LineBorder(Color.GREEN, 2));

        testPanel3.setPreferredSize(new Dimension(FrameGridBag.xSize / 3, FrameGridBag.ySize / 3));
        testPanel3.add(label3);
        testPanel3.setBorder(new LineBorder(Color.GREEN, 2));

       // topSubPanel.add(testPanel, BorderLayout.CENTER);
       // topSubPanel.add(testPanel, BorderLayout.WEST);

        //topSubPanel.add(testPanel, BorderLayout.EAST);


        topPanel.displayThreeHorizontalFrames(testPanel1, testPanel2, testPanel3);
        //topSubPanel.revalidate();
        //topSubPanel.repaint();
        frame.pack();
        frame.setVisible(true);


    }
}
