/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.*;

import java.util.ArrayList;
import java.util.Scanner;

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

    private BaseCard takeCardFromHandAndPlay(Integer cardIndex){
        //Check for trump card
        if(hand.get(cardIndex).getCardType().contains("trump")){
            if(hand.get(cardIndex).getTitle().toLowerCase().contains("geologist")){
                Game.currentTrump = selectTrump();
            } else {
                Game.currentTrump = (TrumpCard) hand.get(cardIndex);
            }
        }
        BaseCard card = hand.get(cardIndex);
        hand.remove(card);
        return card;
    }

    //private

    private void selectCard(){
        int i = 0;
        Scanner scanner = new Scanner(System.in);
        //show cards to player
        System.out.println("Current player is: " + getName());
        System.out.println("Press enter to continue");
        String temp = scanner.nextLine();
        System.out.println("Current Trump is is: " + Game.currentTrump.getDetails());
        if (Game.playedCards.size() > 0) {
            System.out.println("Last Played Card was: " + Game.playedCards.get(Game.playedCards.size()-1));
        }


        System.out.println("Please Select a card ");
        System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavige", "Crustal Abun", "Value" );
        System.out.printf(" %-3d : | %-113s |%n", i, "pass and pickup a card");
        i++;
        for(; i < hand.size(); i++){
            System.out.printf(" %-3d : %s%n",i, hand.get(i-1).getDetails());
        }
        //TODO: Exception check user input
        //add this card to played cards ArrayList.
        Integer cardToPlay = scanner.nextInt() - 1;
        if (cardToPlay.equals(-1)){
            hand.add(Game.deck.pop());
        } else {
            Game.playedCards.add(takeCardFromHandAndPlay(cardToPlay));
        }
    }

    private TrumpCard selectTrump(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please Select a playing category ");
        for(int i = 0; i < Game.trumpCards.size(); i++){
            System.out.println(i + ": " + Game.trumpCards.get(i).getDetails());
        }
        //TODO: Exception check user input
        Integer newTrump = scanner.nextInt();   //set the trump

        return Game.trumpCards.get(newTrump);
    }

    // only used for starting a game
    public TrumpCard playCard(){
        Scanner scanner = new Scanner(System.in);

        selectCard();

        return selectTrump();
    }

    // use during the round
    public void playCard(BaseCard trump){
        System.out.println("Current trump is: " + trump);
        selectCard();

    }
}
