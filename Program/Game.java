/**
 * Created by kurt on 5/09/2016.
 */

import cardsPackage.BaseCard;
import cardsPackage.CardStatic;
import cardsPackage.MineralCard;
import cardsPackage.TrumpCard;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Game {

    static Stack<BaseCard> deck;
    private static ArrayList<BaseCard> rulesCards;
    static ArrayList<TrumpCard> trumpCards;
    static ArrayList<MineralCard> playingCards;
    static ArrayList<BaseCard> playedCards;
    static TrumpCard currentTrump;
    static Player winner;
    static View view;
    static final int geophysicistAndMagnetiteMenuNumber = 99;

    private ArrayList<Player> players;
    private Player dealer;


    public Game() {
        deck = new Stack<BaseCard>();
        rulesCards = new ArrayList<BaseCard>();
        trumpCards = new ArrayList<TrumpCard>();
        players = new ArrayList<Player>();
        playedCards = new ArrayList<BaseCard>();
        playingCards = new ArrayList<>();
        view = new View();

        currentTrump = new TrumpCard("", "", "trump", "None:", " its the first round");

        //sort rule cards from file and create deck
        for (BaseCard card : PListParser.getCardsList()) {
            if (card.getCardType().contains("play")) {
                playingCards.add((MineralCard) card);
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

    //TODO:refactor for MVC
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

            if (players.size() == 5) {
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

    //region helper methods

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

    private TrumpCard selectTrump() {
        String userInput = "";
        Integer number;
        ArrayList<TrumpCard> trumpCardsToDisplay = new ArrayList<>();

        for (TrumpCard card : trumpCards) {
            if (!card.getTitle().toLowerCase().contains("geologist")) {
                trumpCardsToDisplay.add(card);
            }
        }

        do {
            userInput = view.showCardSelectionWithMessage(BaseCard.getCardsAsBase(trumpCardsToDisplay), "Please Select a Trump category ");
            while (!tryParseInt(userInput)) {
                userInput = view.showCardSelectionWithMessage(BaseCard.getCardsAsBase(trumpCardsToDisplay), "Please enter a number. \nSelect a Trump category ");
            }
            number = Integer.parseInt(userInput) - 1;
        }
        while (number < 0 || number > (trumpCards.size()));

        Integer newTrump = number;   //set the trump
        System.out.println("number is = " + number);
        return trumpCards.get(newTrump);
    }

    private TrumpCard checkForShowingGeophysicistAndMagnetite(ArrayList<BaseCard> cardsList) {

        boolean hasGeophysicist = cardsList.contains(CardStatic.getCardByTitle(BaseCard.getCardsAsBase(trumpCards), "Geophysicist"));
        boolean hasMagnetite = cardsList.contains(CardStatic.getCardByTitle(BaseCard.getCardsAsBase(playingCards), "Magnetite"));
        if (hasGeophysicist && hasMagnetite) {
            return new TrumpCard("none", "none", "trump", "Geophysicist And Magnetite", "Play these two cards to play the hand");
        }
        return null;

    }

    private Integer showCardSelection(ArrayList<BaseCard> hand) {

        String userInput;
        //check to see if it is the first round
        if (!Game.currentTrump.getTitle().toLowerCase().contains("none")) {
            userInput = view.showCardSelectionWithPass(hand);
        } else {
            userInput = view.showCardSelection(hand);
        }

        while (!tryParseInt(userInput)) {
            userInput = view.showCardSelectionWithPassAndMessage(hand, "Please enter a number");
        }
        return (Integer.parseInt(userInput));

    }

    private void selectCard(Player currentPlayer) {

        Scanner scanner = new Scanner(System.in);
        boolean cardPlayable = true;

        TrumpCard hasGeophysicistAndMagnetite;
        ArrayList<BaseCard> hand = new ArrayList<>();

        hand.addAll(currentPlayer.getHand());

        hasGeophysicistAndMagnetite = checkForShowingGeophysicistAndMagnetite(hand);
        if (hasGeophysicistAndMagnetite != null) {
            hand.add(hasGeophysicistAndMagnetite);
        }

        //show cards to player
        view.showString("Current player is: " + currentPlayer.getName());
        view.pauseForEnter("");


        Integer number;
        do {
            int i = 0;
            view.showCardWithMessage(Game.currentTrump, "The current trump is:");
            if (Game.playedCards.size() > 0) {
                view.showCardWithMessage(Game.playedCards.get(Game.playedCards.size() - 1), "Last Played Card was: ");
            }

            number = showCardSelection(hand);

            if (number < 1 || hand.get(number - 1).getTitle().contains("Geophysicist And Magnetite")) {
                cardPlayable = true;
            } else {
                if (playedCards.size() > 0) {
                    cardPlayable = isCardPlayable(hand.get(number - 1));
                }
            }
            System.out.println("card playable = " + cardPlayable);
            System.out.println("number = " + number);
        }
        while (number < 0 || number > hand.size() || !cardPlayable);


        Integer cardToPlay = number - 1;

        if (cardToPlay.equals(-1)) {    //are we passing??
            currentPlayer.addToHand(Game.deck.pop());
        } else {
            //TODO: refactor to method
            if (hand.get(cardToPlay).getCardType().contains("trump")) {
                if (hand.get(cardToPlay).getTitle().toLowerCase().contains("geologist")) {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
                    currentTrump = selectTrump();
                    //play another card
                    selectCard(currentPlayer);
                } else if (hand.get(cardToPlay).getTitle().toLowerCase().contains("Geophysicist And Magnetite")) {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(currentPlayer.getHand().indexOf(
                            CardStatic.getCardByTitle(BaseCard.getCardsAsBase(playingCards), "Magnetite"))));
                    Game.playedCards.add(currentPlayer.takeCardFromHand(currentPlayer.getHand().indexOf(
                            CardStatic.getCardByTitle(BaseCard.getCardsAsBase(trumpCards), "Geophysicist"))));
                    currentTrump = selectTrump();
                    //play another card
                    selectCard(currentPlayer);
                } else {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
                    currentTrump = (TrumpCard) hand.get(cardToPlay);
                    //play another card
                    selectCard(currentPlayer);
                }
            } else {
                Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
            }

            if (currentPlayer.getHand().size() < 2) {
                winner = currentPlayer;
            }
        }
    }

    private boolean isCardPlayable(BaseCard card) {
        return (playedCards.get(playedCards.size() - 1).getCardType().toLowerCase().contains("trump")
                || card.getCardType().toLowerCase().contains("trump")
                || card.isThisCardGreaterThan(currentTrump,
                (MineralCard) playedCards.get(playedCards.size() - 1)));
    }

    //endregion
    //TODO: move this into another class for MVC
    public static void main(String[] args) {

        Game myGame = new Game();

        //TODO:
        if (myGame.setPlayers()) {
            System.out.println("Correct number of players entered");
        } else {
            System.out.println("Error setting players");
            //System.exit();
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

        // should be:
        //get player to the left of dealer
        Player currentPlayer = myGame.players.get(playerCounter.increment());

        //get hand and display to player
        //get player to select card
        myGame.selectCard(currentPlayer);

        //get player to select trump
        currentTrump = myGame.selectTrump();

        //TODO: state the top value for that category from the card just played


        while (winner == null) {
            //check deck size and re shuffle if last card has been played
            if (deck.size() < 2) {
                for (BaseCard card : playedCards) {
                    deck.push(card);
                }
                myGame.doShuffle();
            }
            System.out.println("deck size = " + deck.size());
            System.out.println("playedCard size = " + playedCards.size());

            //get next player
            currentPlayer = myGame.players.get(playerCounter.increment());

            //get hand and display to player
            //get player to select card
            myGame.selectCard(currentPlayer);

            //TODO: state the top value for that category from the card just played

        }

        System.out.println("The Winner IS :" + winner);
        winner = null;
    }


    /*** helper ***/

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
