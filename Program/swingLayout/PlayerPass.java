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
        if (controller.game.activePlayers.size() == 1 && (controller.game.finishedPlayers.size() + 1) < controller.game.players.size()) {
            if (controller.game.currentPlayer.getHand().size() > 1) {
                controller.game.resetActivePlayers();
                //controller.frame.refreshCurrentPlayer();
                //TODO:get player to select trump
                //myGame.currentTrump = myGame.selectTrump();
                //TODO: get player to play card !! this should not happen here !! should happen from select trump hand
                //myGame.addPlayedCard(clearCard);
                //myGame.selectCard(myGame.currentPlayer);
                return;
            } else {

                controller.game.checkForDeckShuffle();
                //get next player
                controller.game.getNextActivePlayer();
                controller.frame.refreshCurrentPlayer();
                //myGame.displayCurrentPlayer(myGame.currentPlayer);
                //TODO:get player to select trump
                //myGame.currentTrump = myGame.selectTrump();
                //TODO: get player to play card !! this should not happen here !! should happen from select trump hand
                //myGame.addPlayedCard(clearCard);
                //myGame.selectCard(myGame.currentPlayer);
                return;
            }


        }
        controller.game.getNextActivePlayer();
        controller.frame.showNextPlayer();

    }
}
