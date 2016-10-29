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

    public ShowPlayerTrumpSelect showPlayerTrumpSelect;
    public PlayerSelectTrump playerSelectTrump;


    public Controller(Game game) {
        this.game = game;
        this.showNextPlayer = new ShowNextPlayer(this);
        this.showPlayerGame = new ShowPlayerGame(this);
        this.playerPass = new PlayerPass(this);
        this.playerPlayCard = new PlayerPlayCard(this);
        this.playerSelectCard = new PlayerSelectCard(this);
        this.showPlayerTrumpSelect = new ShowPlayerTrumpSelect(this);
        this.playerSelectTrump = new PlayerSelectTrump(this);

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
