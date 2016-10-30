package swingLayout;

import cardsPackage.BaseCard;
import cardsPackage.TrumpCard;
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

        if(controller.game.currentlySelectedCard.getCardType().toLowerCase().contains("trump")){
            controller.game.currentTrump = (TrumpCard)controller.game.currentlySelectedCard;
            if(!controller.selectTrump){
                //this was not a "Select trump" so work like normal
                //take the selected card from hand and add to played cards
                BaseCard tempCard = controller.game.currentPlayer.takeCardFromHand(
                        controller.game.currentPlayer.getHand().indexOf(controller.game.currentlySelectedCard));
                controller.game.playedCards.add(tempCard);
                controller.game.lastPlayedCard = tempCard;
                if(tempCard.getTitle().toLowerCase().contains("geologist")){
                    // if its the geologist get player to select trump
                    controller.frame.showPlayerSelectTrump(controller.game.trumpCards.get(0));
                } else {
                    if (checkFinishedHand()) {
                        controller.frame.showPlayerGameHand();
                    }
                }
            } else {
                //we just selected a trump do not increment player
                controller.selectTrump = false;
                controller.game.lastPlayedCard = controller.game.currentlySelectedCard;
                controller.frame.showPlayerGameHand();
            }

        } else {
            System.out.println("not a trump");
            System.out.println(controller.game.currentlySelectedCard.getTitle() + " is not beer");

            if (controller.game.isCardPlayable(controller.game.currentlySelectedCard)) {
                //TODO: Check to see if playing card is valid?
                System.out.println("Check playable returned true");
                //take the selected card from hand and add to played cards
                BaseCard tempCard = controller.game.currentPlayer.takeCardFromHand(
                        controller.game.currentPlayer.getHand().indexOf(controller.game.currentlySelectedCard));
                controller.game.playedCards.add(tempCard);
                controller.game.lastPlayedCard = tempCard;
                if (checkFinishedHand()) {
                    if (controller.runGameLogic()) {
                        System.out.println("rungame logic returned true");
                        controller.game.getNextActivePlayer();
                        controller.game.checkForDeckShuffle();
                        controller.frame.showNextPlayer();
                    } else {
                        System.out.println("rungame logic returned false");
                    }
                }
            } else {
                System.out.println(" not a valid card to play");
            }
        }

        //check to add player to finished list


    }

    private boolean checkFinishedHand() {
        if (controller.game.currentPlayer.getHand().size() < 1) {
            controller.game.finishedPlayers.add(controller.game.currentPlayer);
            controller.game.activePlayers.remove(controller.game.currentPlayer);
            if (controller.game.finishedPlayers.size() + 1 == controller.game.players.size()) {
                controller.game.winner = controller.game.finishedPlayers.get(0);
                controller.game.resetActivePlayers();
                controller.game.getNextActivePlayer();
                controller.game.loser = controller.game.currentPlayer;
                controller.frame.showFinishedGame();
                return false;
            } else {
                controller.frame.showPlayerFinishedHand();
                return false;
            }
        }
        return true;
    }
}
