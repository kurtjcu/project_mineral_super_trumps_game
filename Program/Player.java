/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private String name;
    private ArrayList<BaseCard> hand;
    private static ArrayList<TrumpCard> trumps;
    private static ArrayList<BaseCard> playedCards;

    public Player(String name, ArrayList<TrumpCard> trumps, ArrayList<BaseCard> playedCards) {
        this.name = name;
        this.trumps = trumps;
        this.playedCards = playedCards;
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

    private BaseCard takeCardFromHandAndPlay(Integer cardIndex){
        BaseCard card = hand.get(cardIndex);
        hand.remove(card);
        return card;
    }

    public TrumpCard playCard(){
        Scanner scanner = new Scanner(System.in);
        //show cards to player
        System.out.println("Please Select a card ");
        for(int i = 0; i < hand.size(); i++){
            System.out.println(i + ": " + hand.get(i));
        }
        //TODO: Exception check user input
        //add this card to played cards ArrayList.
        Integer cardToPlay = scanner.nextInt();
        takeCardFromHandAndPlay(cardToPlay);

        System.out.println("Please Select a playing category ");
        for(int i = 0; i < trumps.size(); i++){
            System.out.println(i + ": " + trumps.get(i));
        }

        //TODO: Exception check user input
        Integer newTrump = scanner.nextInt();   //set the trump

        return trumps.get(newTrump);
        //select card to play or pass

    }

    public void playCard(BaseCard trump){
        Scanner scanner = new Scanner(System.in);
        //show cards to player
        for(int i = 0; i < hand.size(); i++){
            System.out.println(i + ": " + hand.get(i));
        }
        //select card to play or pass

    }
}
