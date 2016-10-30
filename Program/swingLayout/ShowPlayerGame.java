package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class ShowPlayerGame implements ActionListener {

    Controller controller;
    public ShowPlayerGame(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(controller.game.currentPlayer.getName());
        System.out.println(controller.game.currentPlayer.getName());
        controller.frame.showPlayerGameHand();

    }
}
