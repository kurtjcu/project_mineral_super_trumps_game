package gamePackage;/*
 * Created by kurt on 5/09/2016.
 *
 * Main gamePackage.Game code for Mineral Super Trumps,
 * this is where the game is run from
 */

import cardsPackage.BaseCard;
import cardsPackage.CardStatic;
import cardsPackage.MineralCard;
import cardsPackage.TrumpCard;
import swingLayout.GuiView;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;


public class Game {

    private static Stack<BaseCard> deck;
    private static ArrayList<BaseCard> rulesCards;    //not used yet
    private static ArrayList<TrumpCard> trumpCards;
    private static ArrayList<MineralCard> playingCards;
    private static ArrayList<BaseCard> playedCards;
    private static TrumpCard currentTrump;
    private static View view;
    private static MineralCard clearCard;

    private ArrayList<Player> players;
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> finishedPlayers;
    private Player dealer;
    private static Player winner;
    private static Player loser;

    private final int dealtHandSize = 7; // normal game is 7


    public Game() {
        deck = new Stack<>();
        rulesCards = new ArrayList<>();
        trumpCards = new ArrayList<>();
        playingCards = new ArrayList<>();

        playedCards = new ArrayList<>();

        players = new ArrayList<>();
        activePlayers = new ArrayList<>();
        finishedPlayers = new ArrayList<>();

        view = new View();

        currentTrump = new TrumpCard("", "", "trump", "None:", " its the first round");
        clearCard = new MineralCard("none", "none", "play", "none", "none", "none", "none", new String[]{"zero"},
                new Double[]{0d}, new Double[]{0d}, "zero", "zero", "zero");

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

    private String getDealer() {
        return dealer.getName();
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * Setters
     **/

    private void doShuffle() {
        deck = FisherYatesShuffle.doFisherYatesShuffle(deck);
    }

    public void setPlayers(ArrayList<String> playersNames){
        for(String playerName : playersNames){
            this.players.add(new Player(playerName));
        }
    }

    private Boolean setPlayers() {
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

    private void resetActivePlayers() {
        activePlayers = new ArrayList<>();

        for (Player player : players) {
            if (!finishedPlayers.contains(player)) {
                activePlayers.add(player);
            }
        }
    }

    private Player getNextActivePlayer(Counter playerCounter) {
        int playerNum = playerCounter.increment();
        while (!activePlayers.contains(players.get(playerNum))) {
            playerNum = playerCounter.increment();
        }
        return players.get(playerNum);
    }

    private void displayCurrentPlayer(Player currentPlayer) {
        view.clearScreen();
        //show cards to player
        view.showString("Current player is: " + currentPlayer.getName());
        view.pauseForEnter("");
    }

    private boolean setDealer() {
        if (players.size() > 0) {
            dealer = players.get((int) (Math.random() * (players.size())));
            return true;
        } else {
            return false;
        }
    }

    //region helper methods

    private void dealCards(Counter playerCounter) {
        for (int j = 0; j < dealtHandSize; j++) {
            // deal a card to each player
            for (int i = 0; i < players.size(); i++) {
                players.get(playerCounter.increment()).addToHand(deck.pop());  //give card to next player
            }
        }
    }

    private TrumpCard selectTrump() {
        String userInput;
        Integer number;
        ArrayList<TrumpCard> trumpCardsToDisplay = new ArrayList<>();

        resetActivePlayers();

        for (TrumpCard card : trumpCards) {
            if (!card.getTitle().toLowerCase().contains("geologist")) {
                trumpCardsToDisplay.add(card);
            }
        }

        do {
            userInput = view.showCardSelectionWithMessage(BaseCard.getCardsAsBase(trumpCardsToDisplay),
                    "Please Select a Trump category ");
            while (!tryParseInt(userInput)) {
                userInput = view.showCardSelectionWithMessage(BaseCard.getCardsAsBase(trumpCardsToDisplay),
                        "Please enter a number. \nSelect a Trump category ");
            }
            number = Integer.parseInt(userInput) - 1;
        }
        while (number < 0 || number > (trumpCards.size()));

        Integer newTrump = number;   //set the trump
        System.out.println("number is = " + number);
        return trumpCards.get(newTrump);
    }

    private TrumpCard checkForShowingGeophysicistAndMagnetite(ArrayList<BaseCard> cardsList) {

        boolean hasGeophysicist = cardsList.contains(CardStatic.getCardByTitle(BaseCard.getCardsAsBase(trumpCards),
                "Geophysicist"));
        boolean hasMagnetite = cardsList.contains(CardStatic.getCardByTitle(BaseCard.getCardsAsBase(playingCards),
                "Magnetite"));
        if (hasGeophysicist && hasMagnetite) {
            return new TrumpCard("none", "none", "trump", "Geophysicist And Magnetite",
                    "Play these two cards to play the hand");
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

    private void checkForDeckShuffle() {
        //check deck size and re shuffle if second last card has been played
        if (deck.size() < 2) {
            for (BaseCard card : playedCards) {
                if (!card.equals(clearCard))
                    deck.push(card);
            }
            playedCards.clear();
            doShuffle();
        }
        System.out.println("deck size = " + deck.size());
        System.out.println("playedCard size = " + playedCards.size());
    }

    private void selectCard(Player currentPlayer) {
        boolean cardPlayable = true;

        TrumpCard hasGeophysicistAndMagnetite;
        ArrayList<BaseCard> hand = new ArrayList<>();

        //check for empty hand
        if (currentPlayer.getHand().size() < 1) {
            return;
        }

        //create a temp hand to check for Geo+Mag
        hand.addAll(currentPlayer.getHand());
        hasGeophysicistAndMagnetite = checkForShowingGeophysicistAndMagnetite(hand);
        if (hasGeophysicistAndMagnetite != null) {
            hand.add(hasGeophysicistAndMagnetite);
        }

        Integer number;
        do {
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
            activePlayers.remove(currentPlayer);
        } else {
            //TODO: refactor to method
            //check for trump
            if (hand.get(cardToPlay).getCardType().contains("trump")) {
                //check for geologist
                if (hand.get(cardToPlay).getTitle().toLowerCase().contains("geologist")) {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
                    currentTrump = selectTrump();
                    //play another card
                    selectCard(currentPlayer);
                    //check for Geophysicist && Magnetite
                } else if (hand.get(cardToPlay).getTitle().toLowerCase().contains("Geophysicist And Magnetite")) {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(currentPlayer.getHand().indexOf(
                            CardStatic.getCardByTitle(BaseCard.getCardsAsBase(playingCards), "Magnetite"))));
                    Game.playedCards.add(currentPlayer.takeCardFromHand(currentPlayer.getHand().indexOf(
                            CardStatic.getCardByTitle(BaseCard.getCardsAsBase(trumpCards), "Geophysicist"))));
                    currentTrump = selectTrump();
                    //play another card
                    selectCard(currentPlayer);
                    //fall through to normal trump
                } else {
                    Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
                    currentTrump = (TrumpCard) hand.get(cardToPlay);
                    resetActivePlayers();
                    //play another card
                    selectCard(currentPlayer);
                }
                //normal playing card
            } else {
                Game.playedCards.add(currentPlayer.takeCardFromHand(cardToPlay));
            }


            //TODO: why does this have to be 2 shouldn't it be 1 << something smells here...
            if (currentPlayer.getHand().size() < 2) {
                finishedPlayers.add(currentPlayer);
                activePlayers.remove(currentPlayer);
                if (finishedPlayers.size() + 1 == players.size()) {
                    winner = finishedPlayers.get(0);
                    loser = currentPlayer;
                }
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
    public static void oldMain(String[] args) {

        Game myGame = new Game();

        if (myGame.setPlayers()) {
            System.out.println("Correct number of players entered");
        } else {
            System.out.println("Error setting players");
            //System.exit();
            return;
        }

        myGame.resetActivePlayers();

        if (myGame.setDealer()) {
            System.out.println("The Dealer is " + myGame.getDealer());
        } else {
            System.out.println("Cannot set dealer...");
            return;
        }

        //create counter for peoples turns
        Counter playerCounter = new Counter(myGame.players.size(), myGame.players.indexOf(myGame.dealer));

        myGame.doShuffle();

        myGame.dealCards(playerCounter);

        // should be:
        //get player to the left of dealer
        Player currentPlayer = myGame.getNextActivePlayer(playerCounter);

        //show the current player their cards
        myGame.displayCurrentPlayer(currentPlayer);

        //get hand and display to player
        //get player to select card
        myGame.selectCard(currentPlayer);

        //get player to select trump
        currentTrump = myGame.selectTrump();

        while (winner == null) {

            myGame.checkForDeckShuffle();

            //get next player
            currentPlayer = myGame.getNextActivePlayer(playerCounter);

            myGame.displayCurrentPlayer(currentPlayer);
            //get hand and display to player
            //get player to select card
            myGame.selectCard(currentPlayer);

            //check for all but one has passed and its not the only the looser left
            if (myGame.activePlayers.size() == 1 && (myGame.finishedPlayers.size() + 1) < myGame.players.size()) {

                if (currentPlayer.getHand().size() > 1) {
                    myGame.resetActivePlayers();
                    currentTrump = myGame.selectTrump();
                    playedCards.add(clearCard);
                    myGame.selectCard(currentPlayer);
                } else {
                    myGame.checkForDeckShuffle();
                    //get next player
                    currentPlayer = myGame.getNextActivePlayer(playerCounter);
                    myGame.displayCurrentPlayer(currentPlayer);
                    currentTrump = myGame.selectTrump();
                    playedCards.add(clearCard);
                    //get hand and display to player
                    //get player to select card
                    myGame.selectCard(currentPlayer);
                }
            }
        }
        System.out.println("The Winner is :" + winner);
        System.out.println("and " + loser + " Lost...");
    }

    public static void main(String[] args) {
        Game game = new Game();
        GuiView guiView = new GuiView(game);

        //add players to game
        guiView.startAddPlayerFrame();

        //guiView.startGameFrame();

        //guiView.gameFrame.createAndShowGUI();
    }


    /*** helper ***/

    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
