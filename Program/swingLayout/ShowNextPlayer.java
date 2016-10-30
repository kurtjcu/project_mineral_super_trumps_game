package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class ShowNextPlayer implements ActionListener {

    Controller controller;
    public ShowNextPlayer(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("hello");
        controller.frame.showPlayerGameHand();
    }
}
