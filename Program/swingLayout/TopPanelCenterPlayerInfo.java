package swingLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelCenterPlayerInfo extends JPanel {


    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBag.ySize * .15));
    int gameWidth = (int) (Math.round(FrameGridBag.xSize * .1));

    public JLabel playerNumber;
    public JLabel playerName;
    public JLabel playerStatus;




    public TopPanelCenterPlayerInfo() {
        super(new BorderLayout());

        playerNumber = new JLabel("Number", SwingConstants.CENTER);
        playerName = new JLabel("Name", SwingConstants.CENTER);
        playerStatus = new JLabel("Status", SwingConstants.CENTER);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        this.add(playerNumber, BorderLayout.PAGE_START);
        this.add(playerName, BorderLayout.CENTER);
        this.add(playerStatus, BorderLayout.PAGE_END);

        this.setVisible(true);

    }

    public void setPlayerName(String playerName){
        this.playerName.setFont(largeFont);
        this.playerName.setText(playerName);
    }


    public void setPlayerNumber(String playerNumber) {
        this.playerNumber.setFont(largeFont);
        this.playerNumber.setText(playerNumber);
    }

    public void setPlayerStatus(String playerStatus) {
        this.playerStatus.setFont(largeFont);
        this.playerStatus.setText(playerStatus);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("TestingTopPanelCenterPlayer");
        TopPanelCenterPlayerInfo playerInfo = new TopPanelCenterPlayerInfo();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(playerInfo);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

        playerInfo.setPlayerName("Jim Bob");
        playerInfo.setPlayerNumber("Player 1");
        playerInfo.setPlayerStatus("Bored");

    }
}