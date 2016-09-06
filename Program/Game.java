/**
 * Created by kurt on 5/09/2016.
 */

import java.util.*;

import cardsPackage.*;

import javax.smartcardio.Card;


public class Game {

    private Stack<BaseCard> deck;
    private ArrayList<BaseCard> rulesCards;
    private ArrayList<Player> players;
    private Player dealer;

    public Game(){
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        players = new ArrayList<Player>();

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

    /** Getters **/

    public String getDealer(){
        return dealer.getName();
    }

    /** Setters **/

    public void doShuffle(){
        deck = FisherYatesShuffle.FisherYatesShuffle(deck);
    }

    public  Boolean setPlayers(String[] aPlayers) {

        for(String player : aPlayers){
            players.add(new Player(player));
        }
        if(players.size() > 2 && players.size() < 6){
            return true;
        } else {
            return false;
        }
    }

    public boolean setDealer(){
        if(players.size() > 0){
            dealer = players.get((int)(Math.random() * (players.size())));
            return true;
        } else {
            return false;
        }

    }

    public void dealCards(){
        Counter playerCounter = new Counter(players.size(), players.indexOf(dealer));
        System.out.println("Index of Dealer " + players.indexOf(dealer));


        for (int j = 1; j < 8; j++) {
            // deal a card to each player
            for(int i = 0; i < players.size(); i++){

                players.get(playerCounter.increment()).addToHand(deck.pop());  //give card to next player
            }
        }


    }

    public static void main(String[] args) {

        Game myGame = new Game();

        String [] aTempPlayerNames = {"Bob", "Jack", "Me"};

        //TODO: change to while loop when getting players from command line
        if(myGame.setPlayers(aTempPlayerNames)){
            System.out.println("Correct number of players entered");
        } else {
            System.out.println("please enter the correct number of players ( 3 to 5)...");
            return;
        }

        if(myGame.setDealer()){
            System.out.println("The Dealer is " + myGame.getDealer());
        } else {
            System.out.println("Cannot set dealer...");
            return;
        }

        myGame.doShuffle();

        //TODO: Deal Cards to players (cycle through each player).
        // get index of dealer
        // for 1 to 8
        //      for  1 to numPlayers
        //          give card to dealerindex+i until i = playercount - 1 then set i to 0
        //
        // the above should be a function i.e. getNextPlayer as it will be reused then playing the game...



        //TODO: display hands to each player

        //TODO: playGame Loop..

        for(BaseCard card : myGame.deck){
            System.out.println("Name of Card: " + card.getTitle());
        }


        myGame.dealCards();

        for(Player player : myGame.players){
            ArrayList<BaseCard> hand = player.getHand();
            System.out.println(player.getName());

            for(BaseCard card : hand){
                System.out.println(card.getTitle());
            }
        }





    }
}
