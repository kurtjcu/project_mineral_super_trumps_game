package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class PlayerPass implements ActionListener {

    Controller controller;
    public PlayerPass(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        controller.game.currentPlayer.addToHand(controller.game.deck.pop());
        controller.game.activePlayers.remove(controller.game.currentPlayer);
        controller.frame.showNextPlayer();

    }
}
