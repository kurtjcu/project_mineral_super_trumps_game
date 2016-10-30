package gamePackage;
/*
 * Created by kurt on 5/09/2016.
 *
 * Holds player details such as name also holds the players hand
 */

import cardsPackage.BaseCard;
import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<BaseCard> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    /**
     * setters
     **/

    public void addToHand(BaseCard card) {
        hand.add(card);
    }

    /**
     * Getters
     **/

    public String getName() {
        return name;
    }

    public ArrayList<BaseCard> getHand() {
        return hand;
    }


    public BaseCard takeCardFromHand(int cardIndex) {
        BaseCard card = hand.get(cardIndex);
        hand.remove(card);
        return card;
    }


    @Override
    public String toString() {
        return name;
    }
}
