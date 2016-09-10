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
    private BaseCard currentTrump;

    public Player(String name, ArrayList<TrumpCard> trumps, TrumpCard currentTrump, ArrayList<BaseCard> playedCards) {
        this.name = name;
        this.trumps = trumps;
        this.currentTrump = currentTrump;
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
        //TODO: Check for trump card
        if(hand.get(cardIndex).getCardType().contains("trump")){
            while(hand.get(cardIndex).getTitle().toLowerCase().contains("geologist")){
                selectTrump();
            }
            //TODO setCurrentTrump
        }
        BaseCard card = hand.get(cardIndex);
        hand.remove(card);
        return card;
    }

    private void selectCard(){
        Scanner scanner = new Scanner(System.in);
        //TODO: there should be a way to lookup card details here.
        //show cards to player
        System.out.println("Current player is: " + getName());
        System.out.println("Press enter to continue");
        String temp = scanner.nextLine();
        System.out.println("Please Select a card ");
        System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavige", "Crustal Abun", "Value" );
        for(int i = 0; i < hand.size(); i++){
            System.out.printf(" %-3d : %s%n",i, hand.get(i).getDetails());
        }
        //TODO: Exception check user input
        //add this card to played cards ArrayList.
        Integer cardToPlay = scanner.nextInt();
        takeCardFromHandAndPlay(cardToPlay);
    }

    private TrumpCard selectTrump(){
        Scanner scanner = new Scanner(System.in);

        //TODO: there should be a way for the user to see what the details for the card are here.
        System.out.println("Please Select a playing category ");
        for(int i = 0; i < trumps.size(); i++){
            System.out.println(i + ": " + trumps.get(i).getDetails());
        }
        //TODO: Exception check user input
        Integer newTrump = scanner.nextInt();   //set the trump

        return trumps.get(newTrump);
    }

    public TrumpCard playCard(){
        Scanner scanner = new Scanner(System.in);

        selectCard();

        return selectTrump();
    }

    public void playCard(BaseCard trump){
        System.out.println("Current trumo is: " + trump.getDetails());
        selectCard();

    }
}
