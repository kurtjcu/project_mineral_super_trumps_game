/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.BaseCard;

import java.util.ArrayList;

class Player {

    private String name;
    private ArrayList<BaseCard> hand;


    Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    /**
     * setters
     **/

    void addToHand(BaseCard card) {
        hand.add(card);
    }

    /**
     * Getters
     **/

    String getName() {
        return name;
    }

    ArrayList<BaseCard> getHand() {
        return hand;
    }


    BaseCard takeCardFromHand(int cardIndex) {
        BaseCard card = hand.get(cardIndex);
        hand.remove(card);
        return card;
    }


    @Override
    public String toString() {
        return name;
    }
}
