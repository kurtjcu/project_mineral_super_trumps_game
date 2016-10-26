package swingLayout;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 26/10/2016.
 */
public class AddPlayerAddPlayers extends JPanel {

    int gameHeight = (int) (Math.round(AddPlayerFrame.ySize * .6));
    int gameWidth = (int) (Math.round(AddPlayerFrame.xSize * .3));


    ArrayList<JLabel> labels;
    ArrayList<JTextField> playerNames;
    JButton startGame;

    public AddPlayerAddPlayers(){
        this.setSize(gameWidth, gameHeight);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setupLabels();
        setupPlayerNames();
        startGame = new JButton("Start Game");

        for (int i = 0; i < 5; i++){
            this.add(labels.get(i));
            this.add(playerNames.get(i));
        }
        this.add(startGame);


    }

    void setupLabels(){
        labels = new ArrayList<>();
        for(int i = 1; i < 6; i++){
            labels.add(new JLabel("Player " + i + " Name:"));
        }
    }

    void setupPlayerNames(){
        playerNames = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            playerNames.add(new JTextField());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add players test");

        AddPlayerAddPlayers testFrame = new AddPlayerAddPlayers();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(testFrame);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }

}
