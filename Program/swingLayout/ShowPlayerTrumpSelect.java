package swingLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class ShowPlayerTrumpSelect implements ActionListener {

    Controller controller;
    public ShowPlayerTrumpSelect(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {

        controller.frame.showPlayerSelectTrump();

    }
}
