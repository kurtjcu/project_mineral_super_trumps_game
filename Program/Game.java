/**
 * Created by kurt on 5/09/2016.
 */

import java.util.*;

import cardsPackage.*;

import javax.smartcardio.Card;


public class Game {

    public static Stack<BaseCard> deck;
    public static ArrayList<BaseCard> rulesCards;
    public static ArrayList<TrumpCard> trumpCards;
    private ArrayList<Player> players;
    private Player dealer;
    public static ArrayList<BaseCard> playedCards;
    public static TrumpCard currentTrump;

    public Game(){
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        trumpCards = new ArrayList<TrumpCard>();
        players = new ArrayList<Player>();
        playedCards = new ArrayList<BaseCard>();

        ArrayList<BaseCard> aDeck = PListParser.getCardsList();

        currentTrump = new TrumpCard("","","trump","None:"," its the first round");

        //sort rule cards from file and create deck
        for (BaseCard card : aDeck) {
            if (card.getCardType().contains("play")) {
                deck.push(card);
            } else if (card.getCardType().contains("trump")) {
                trumpCards.add((TrumpCard) card);
                deck.push(card);
            } else if(card.getCardType().contains("rule")) {
                rulesCards.add(card);

            }
        }
        System.out.println("Full Deck size = " + deck.size());
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

        for(BaseCard card : myGame.deck){
            System.out.println("Name of Card: " + card.getTitle());
        }


        myGame.dealCards();



        //TODO: display hands to each player

        for(Player player : myGame.players){
            ArrayList<BaseCard> hand = player.getHand();
            System.out.println(player.getName());

            for(BaseCard card : hand){
                System.out.println(card.getTitle());
            }
        }



        //TODO: playGame Loop..

        //while (true) {
        BaseCard trump;
        Counter playerCounter = new Counter(myGame.players.size(), myGame.players.indexOf(myGame.dealer));   //create counter
        myGame.currentTrump = myGame.players.get(playerCounter.increment()).playCard();  //player to play card
        System.out.println("firstTrump = " + myGame.currentTrump);


            while(deck.size() > 1){
                System.out.println("deck size = " + deck.size());
                System.out.println("playedCard size = " + myGame.playedCards.size());
                myGame.players.get(playerCounter.increment()).playCard(myGame.currentTrump);  //player to play card
            }

        //}

        /****Playgame Loop
         *
         * while(not quit && everyone still has a card)
         * {
         *      while(still cards in deck)
         *      {
         *          Counter playerCounter = new Counter(players.size(), players.indexOf(dealer));   //create counter
         *          players.get(playerCounter.increment()).playCard();  //player to play card
         *      }
         * }
         *
         */









    }
}
