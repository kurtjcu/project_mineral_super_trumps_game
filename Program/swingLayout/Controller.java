package swingLayout;

import gamePackage.Game;

import javax.swing.*;


/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class Controller extends JFrame{

    public Frame frame;

    public static Game game;

    public Controller() {

    }

    //public void updatePlayer

    public Controller(Game game) {
        this.game = game;
    }

    public static void main(String[] args) {

        Controller controller = new Controller();
        controller.frame = new Frame();

        controller.frame.pack();
        controller.frame.showNextPlayer("Jimbob");

        controller.frame.showPlayerGameHand();

    }
}
