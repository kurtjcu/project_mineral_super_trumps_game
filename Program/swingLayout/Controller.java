package swingLayout;

import gamePackage.Game;

import javax.swing.*;


/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class Controller extends JFrame{

    public Frame frame;

    public Game game;

    public Controller() {

    }

    public ShowNextPlayer showNextPlayer; // = new ShowNextPlayer(game);
    public ShowPlayerGame showPlayerGame;
    public PlayerPass playerPass;
    public PlayerPlayCard playerPlayCard;
    public PlayerSelectCard playerSelectCard;


    public Controller(Game game) {
        this.game = game;
        showNextPlayer = new ShowNextPlayer(this);
        showPlayerGame = new ShowPlayerGame(this);
        playerPass = new PlayerPass(this);
        playerPlayCard = new PlayerPlayCard(this);
        playerSelectCard = new PlayerSelectCard(this);

    }

    /*
    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.frame = new Frame();

        controller.frame.pack();
        controller.frame.showNextPlayer("Jimbob");

        //controller.frame.showPlayerGameHand();

    }
    */
}
