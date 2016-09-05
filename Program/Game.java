/**
 * Created by kurt on 5/09/2016.
 */

import java.util.*;

import cardsPackage.*;


public class Game {

    private Stack<BaseCard> deck;
    private ArrayList<BaseCard> rulesCards;

    public Game(){
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        ArrayList<BaseCard> aDeck = PListParser.getCardsList();

        //sort rule cards from file and create deck
        for (BaseCard card : aDeck) {
            if (card.getCardType().contains("play") || card.getCardType().contains("trump")) {
                deck.push(card);
            } else if (card.getCardType().contains("rule")) {
                rulesCards.add(card);
            }
        }
    }


    public void doShuffle(){
        deck = FisherYatesShuffle.FisherYatesShuffle(deck);
    }

    public static void main(String[] args) {

        Game myGame = new Game();
        myGame.doShuffle();
        for(BaseCard card : myGame.deck){
            System.out.println("Name of Card: " + card.getTitle());
        }




    }
}
