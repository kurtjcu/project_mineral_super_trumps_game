/**
 * Created by kurt on 5/09/2016.
 */

import java.util.*;

import cardsPackage.*;


public class Game {

    private Stack<BaseCard> deck;
    private ArrayList<BaseCard> rulesCards;

    Game(){
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        ArrayList<BaseCard> aDeck = PListParser.getCardsList();

        for (BaseCard card : aDeck){
            if(card.getCardType().contains("play") || card.getCardType().contains("trump")){
                deck.push(card);
            } else if(card.getCardType().contains("rule")){
                rulesCards.add(card);
            }
        }
    }


    public static void main(String[] args) {

         Game myGame = new Game();



    }
}
