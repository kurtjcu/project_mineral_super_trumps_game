package swingLayout;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

import gamePackage.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelCenter extends JPanel {

    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .333));
    int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize * .333));
    ArrayList<TopPanelCenterPlayerInfo> playerPanels = new ArrayList<>();

    public TopPanelCenter() {
        super(new FlowLayout());

        for(int i = 1; i < 6; i++ ){
            TopPanelCenterPlayerInfo tempPlayerInfo = new TopPanelCenterPlayerInfo();
            tempPlayerInfo.setPlayerNumber("Player " + i);
            //tempPlayerInfo.setPlayerName(players.get(i-1).getName());   //TODO this will throw an exception if it is empty
            this.add(tempPlayerInfo);
            playerPanels.add(tempPlayerInfo);
        }

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }

    public TopPanelCenter(ArrayList<Player> players){
        this();
        setPlayers(players);
    }



    public void setPlayers(ArrayList<Player> players){
        for(int i = 0; i < players.size(); i++){
            setPlayersName(i, players.get(i).getName());
        }

        this.revalidate();
        this.repaint();

    }

    public void setPlayersName(int index, String name){
        playerPanels.get(index).setPlayerName(name);
    }

    public void setPlayersStatus(int index, String status){
        playerPanels.get(index).setPlayerStatus(status);
    }

    public static void main(String[] args) {

        TopPanelCenter topPanelCenter = new TopPanelCenter();
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
