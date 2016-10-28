package swingLayout;

import javax.swing.*;
import java.awt.Frame;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class Controller extends JFrame{

    public Frame frame;

    public Controller() {

    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.frame = new Frame();

        controller.frame.pack();

        controller.frame

        controller.frame.showNextPlayer("Jimbob");
        controller.frame.showNextPlayer("Fairy Fucker");

        controller.frame.showPlayerGameHand();

    }
}
