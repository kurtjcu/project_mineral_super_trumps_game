package swingLayout;


import gamePackage.Game;
import gamePackage.Player;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class TopPanelRightFinishedPlayers extends JPanel {


    int gameHeight = (int) (Math.round(Frame.ySize * .3));
    int gameWidth = (int) (Math.round(Frame.xSize * .16));

    Controller controller;

    public JLabel title;
    public JList finishedPlayerList;
    public DefaultListModel listModel = new DefaultListModel();


    public TopPanelRightFinishedPlayers(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;

        title = new JLabel("Finished Players", SwingConstants.CENTER);
        title.setFont(Frame.largeFont);
        finishedPlayerList = new JList(listModel);
        finishedPlayerList.setLayoutOrientation(JList.VERTICAL);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));

        setFinishedPlayers();

        this.add(title, BorderLayout.PAGE_START);
        this.add(finishedPlayerList, BorderLayout.CENTER);
        this.setVisible(true);

    }

    public void addFinishedNameToList(String name){

        this.listModel.addElement(name);
    }


    public void setFinishedPlayers(){

        for(Player player : controller.game.finishedPlayers){
            this.listModel.addElement(player.getName());
        }

    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("TestingTopPanelCenterPlayer");
        TopPanelRightFinishedPlayers finishedPlayers = new TopPanelRightFinishedPlayers(new Controller(new Game()));

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
