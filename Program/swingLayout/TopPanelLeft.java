package swingLayout;

import gamePackage.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelLeft extends JPanel {

    public JPanel topPanel = new JPanel(new BorderLayout());
    public JPanel bottomPanel = new JPanel(new BorderLayout());

    int gameHeight = (int) (Math.round(Frame.ySize * .333));
    int gameWidth = (int) (Math.round(Frame.xSize * .333));
    JSplitPane splitPane;


    public TopPanelLeft() {
        super(new BorderLayout());

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                topPanel, bottomPanel);
        this.add(splitPane, BorderLayout.CENTER);
        splitPane.setResizeWeight(0.5);
        setTopPanel("Mineral Supertrumps");
        setBottomPanel("None");

        this.setVisible(true);

    }




    public void setTopPanel(String text){
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(Frame.largeFont);
        topPanel.add(label);

    }

    public void setBottomPanel(String player){
        JLabel label = new JLabel("Current Player: " + player, SwingConstants.CENTER);
        label.setFont(Frame.largeFont);
        bottomPanel.removeAll();
        bottomPanel.add(label);
    }

    public void setBottomPanel(Player player){
        JLabel label = new JLabel("Current Player: " + player.getName(), SwingConstants.CENTER);
        label.setFont(Frame.largeFont);
        bottomPanel.removeAll();
        bottomPanel.add(label);
    }


    public static void main(String[] args) {

        TopPanelLeft topPanelLeft = new TopPanelLeft();
        topPanelLeft.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(topPanelLeft);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        frame.pack();

    }
}
