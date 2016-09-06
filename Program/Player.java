/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.*;

import java.util.ArrayList;

public class Player {

    private String name;
    private ArrayList<BaseCard> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    /** setters **/

    public void addToHand(BaseCard card) {
        hand.add(card);
    }

    /** Getters **/

    public String getName(){
        return name;
    }

    public ArrayList<BaseCard> getHand(){
        return hand;
    }
}
