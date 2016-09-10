/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {

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

    public ArrayList<BaseCard> getHand() {
        return hand;
    }

    private BaseCard takeCardFromHandAndPlay(Integer cardIndex) {
        //Check for trump card
        if (hand.get(cardIndex).getCardType().contains("trump")) {
            if (hand.get(cardIndex).getTitle().toLowerCase().contains("geologist")) {
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

    private void selectCard() {

        Scanner scanner = new Scanner(System.in);
        //show cards to player
        System.out.println("Current player is: " + getName());
        System.out.println("Press enter to continue");
        String temp = scanner.nextLine();
        //scanner = new Scanner(System.in);

        Integer number = -1;
        do {
            int i = 0;
            System.out.println("Current Trump is is: " + Game.currentTrump.getDetails());
            if (Game.playedCards.size() > 0) {
                System.out.println("Last Played Card was: " + Game.playedCards.get(Game.playedCards.size() - 1));
            }


            System.out.println("Please Select a card ");
            System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavige", "Crustal Abun", "Value");
            if (!Game.currentTrump.getTitle().toLowerCase().contains("none")) {
                System.out.printf(" %-3d : | %-113s |%n", i, "pass and pickup a card");
            }
            i++;
            for (; i < hand.size(); i++) {
                System.out.printf(" %-3d : %s%n", i, hand.get(i - 1).getDetails());
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number");
                scanner.next();
            }
            number = scanner.nextInt();

        } while (number < 0 || number > hand.size());


        Integer cardToPlay = number - 1;

        if (cardToPlay.equals(-1)) {
            hand.add(Game.deck.pop());
        } else {
            Game.playedCards.add(takeCardFromHandAndPlay(cardToPlay));
            if (hand.size() < 2) {
                Game.winner = this;
            }
        }
    }

    private TrumpCard selectTrump() {
        Scanner scanner = new Scanner(System.in);
        Integer number = -1;
        do {
            System.out.println("Please Select a Trump category ");
            for (int i = 0; i < Game.trumpCards.size(); i++) {
                //hide geologist card when shoeing cards
                if (!Game.trumpCards.get(i).getTitle().toLowerCase().contains("geologist")) {
                    System.out.println(i + ": " + Game.trumpCards.get(i).getDetails());
                }
            }
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a number");
                scanner.next();
            }
            number = scanner.nextInt();
        }
        while (number < 0 || number > (Game.trumpCards.size() - 1));  //TODO: make this check for geologist instead of size-1

        Integer newTrump = number;   //set the trump

        return Game.trumpCards.get(newTrump);
    }

    // only used for starting a game
    TrumpCard playCard() {
        Scanner scanner = new Scanner(System.in);
        selectCard();
        return selectTrump();
    }

    // use during the round
    void playCard(BaseCard trump) {
        System.out.println("Current trump is: " + trump);
        selectCard();

    }

    @Override
    public String toString() {
        return name;
    }
}
