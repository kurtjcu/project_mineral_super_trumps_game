/**
 * Created by kurt on 5/09/2016.
 */

import java.util.*;

import cardsPackage.*;


public class Game {

    static Stack<BaseCard> deck;
    private static ArrayList<BaseCard> rulesCards;
    static ArrayList<TrumpCard> trumpCards;
    static ArrayList<BaseCard> playedCards;
    static TrumpCard currentTrump;
    static Player winner;

    private ArrayList<Player> players;
    private Player dealer;


    public Game() {
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        trumpCards = new ArrayList<TrumpCard>();
        players = new ArrayList<Player>();
        playedCards = new ArrayList<BaseCard>();

        currentTrump = new TrumpCard("", "", "trump", "None:", " its the first round");

        //sort rule cards from file and create deck
        for (BaseCard card : PListParser.getCardsList()) {
            if (card.getCardType().contains("play")) {
                deck.push(card);
            } else if (card.getCardType().contains("trump")) {
                trumpCards.add((TrumpCard) card);
                deck.push(card);
            } else if (card.getCardType().contains("rule")) {
                rulesCards.add(card);

            }
        }
        System.out.println("Full Deck size = " + deck.size());
    }

    /**
     * Getters
     **/

    public String getDealer() {
        return dealer.getName();
    }

    /**
     * Setters
     **/

    public void doShuffle() {
        deck = FisherYatesShuffle.doFisherYatesShuffle(deck);
    }

    public Boolean setPlayers() {
        Scanner scanner = new Scanner(System.in);
        boolean keepGoing = true;
        do {
            System.out.println("Please Enter a player Name: ");
            if (players.size() >= 3) {
                System.out.println("Type \"stop\" to finish entering players");
            }

            String playerName = scanner.next();
            if (playerName.toLowerCase().trim().equals("stop") && players.size() > 2) {
                keepGoing = false;
            } else {
                players.add(new Player(playerName));
            }

            if(players.size() == 5) {
                keepGoing = false;
            }
            System.out.println("Number of current player: " + players.size());
        } while (keepGoing || players.size() < 3);

        System.out.println("Current players are: " + players.toString());

        return (players.size() > 2 && players.size() < 6);

    }

    public boolean setDealer() {
        if (players.size() > 0) {
            dealer = players.get((int) (Math.random() * (players.size())));
            return true;
        } else {
            return false;
        }
    }

    public void dealCards() {
        Counter playerCounter = new Counter(players.size(), players.indexOf(dealer));
        //System.out.println("Index of Dealer " + players.indexOf(dealer));
        for (int j = 1; j < 8; j++) {
            // deal a card to each player
            for (int i = 0; i < players.size(); i++) {
                players.get(playerCounter.increment()).addToHand(deck.pop());  //give card to next player
            }
        }
    }



    //TODO: move this into another class??
    public static void main(String[] args) {

        Game myGame = new Game();

        if (myGame.setPlayers()) {
            System.out.println("Correct number of players entered");
        } else {
            System.out.println("please enter the correct number of players ( 3 to 5)...");
            return;
        }

        if (myGame.setDealer()) {
            System.out.println("The Dealer is " + myGame.getDealer());
        } else {
            System.out.println("Cannot set dealer...");
            return;
        }

        myGame.doShuffle();

        myGame.dealCards();

        Counter playerCounter = new Counter(myGame.players.size(), myGame.players.indexOf(myGame.dealer));   //create counter

        //need to find a way to extract all the calls to sout from here inside player and find a spin in the MVC for it
        currentTrump = myGame.players.get(playerCounter.increment()).playCard();  //player to play card
        //System.out.println("firstTrump = " + currentTrump);


        while (winner == null) {
            //check deck size and re shuffle if last card has been played
            if (deck.size() > 1) {
                for (BaseCard card : playedCards) {
                    deck.push(card);
                }
                myGame.doShuffle();
            }
            System.out.println("deck size = " + deck.size());
            System.out.println("playedCard size = " + playedCards.size());
            myGame.players.get(playerCounter.increment()).playCard(currentTrump);  //player to play card
        }

        System.out.println("The Winner IS :" + winner);
        winner = null;
    }
}
