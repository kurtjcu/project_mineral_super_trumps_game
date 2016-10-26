package swingLayout;

import gamePackage.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelRightFinishedPlayers extends JPanel {


    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .3));
    int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize * .16));

    public JLabel title;
    public JList finishedPlayerList;
    public ArrayList<String> listNames = new ArrayList<>();
    public DefaultListModel listModel = new DefaultListModel();






    public TopPanelRightFinishedPlayers() {
        super(new BorderLayout());

        title = new JLabel("Finished Players", SwingConstants.CENTER);
        title.setFont(largeFont);
        finishedPlayerList = new JList(listModel);
        finishedPlayerList.setLayoutOrientation(JList.VERTICAL);



        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        this.add(title, BorderLayout.PAGE_START);
        this.add(finishedPlayerList, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public void addFinishedNameToList(String name){

        this.listModel.addElement(name);
    }

    public void setFinishedPlayers(ArrayList<Player> players){

        for(Player player : players){
            this.listModel.addElement(player.getName());
        }

    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("TestingTopPanelCenterPlayer");
        TopPanelRightFinishedPlayers finishedPlayers = new TopPanelRightFinishedPlayers();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(finishedPlayers);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();

        finishedPlayers.addFinishedNameToList("Superman");
        finishedPlayers.addFinishedNameToList("Batman");
        finishedPlayers.addFinishedNameToList("Fatman");
    }
}
