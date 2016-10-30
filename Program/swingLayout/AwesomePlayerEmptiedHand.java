package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class AwesomePlayerEmptiedHand implements ActionListener {

    Controller controller;
    public AwesomePlayerEmptiedHand(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
            controller.game.getNextActivePlayer();
            controller.game.checkForDeckShuffle();
            controller.frame.showNextPlayer();
    }
}
