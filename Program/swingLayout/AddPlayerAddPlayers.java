package swingLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 26/10/2016.
 */
public class AddPlayerAddPlayers extends JPanel implements ActionListener{

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
        startGame = new JButton("Start gamePackage.Game");

        for (int i = 0; i < 5; i++){
            this.add(labels.get(i));
            this.add(playerNames.get(i));
        }

        this.add(startGame);

        startGame.addActionListener(this);
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

    public void actionPerformed(ActionEvent e) {
        ArrayList<String> names = new ArrayList<>();
        int numNames = 0;

        for(JTextField playerName :  playerNames){
            String name = playerName.getText();
            if(!name.isEmpty()){
                numNames++;
                names.add(playerName.getText());
                System.out.println(name);
            }

        }

        if(numNames > 2){

            GuiView.game.setPlayers(names);
            GuiView.game.resetActivePlayers();
            GuiView.game.setDealer();
            GuiView.game.setPlayerCounter(GuiView.game);
            GuiView.game.doShuffle();
            GuiView.game.dealCards(GuiView.game.playerCounter);
            GuiView.game.getNextActivePlayer();

            GuiView.addPlayerFrame.dispose();
            GuiView.startGameFrame();
        } else {
            //TODO: show warning and re request
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
