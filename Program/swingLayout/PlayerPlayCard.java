package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class PlayerPlayCard implements ActionListener {

    Controller controller;
    public PlayerPlayCard(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("this is where i have to add select card logic");
        /*
        controller.game.currentPlayer.addToHand(controller.game.deck.pop());
        controller.game.activePlayers.remove(controller.game.currentPlayer);
        controller.frame.showNextPlayer();
        */

    }
}
