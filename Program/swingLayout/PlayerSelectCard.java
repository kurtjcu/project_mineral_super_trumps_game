package swingLayout;

import cardsPackage.BaseCard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by kurt.Schoenhoff on 29/10/2016.
 */
public class PlayerSelectCard implements ActionListener {

    Controller controller;
    BaseCard card;

    public PlayerSelectCard(Controller controller){
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {

        System.out.println("this is where i change the selected card");

        card = (BaseCard)((CardPanelNormal)e.getSource()).card;

        System.out.println("selected card: " + card.getTitle());

        controller.frame.showPlayerGameHand(card);
        /*
        controller.game.currentPlayer.addToHand(controller.game.deck.pop());
        controller.game.activePlayers.remove(controller.game.currentPlayer);
        controller.frame.showNextPlayer();
        */

    }
}
