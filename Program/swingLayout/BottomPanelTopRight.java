package swingLayout;

import cardsPackage.BaseCard;
import cardsPackage.MineralCard;
import cardsPackage.TrumpCard;
import gamePackage.Game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class BottomPanelTopRight extends JPanel {


    int gameHeight = (int) (Math.round(Frame.ySize * .333));
    int gameWidth = (int) (Math.round(Frame.xSize * .333));

    Controller controller;

    JPanel topTitles;
    JPanel centerCards;
    JPanel bottomDetails;

    JLabel lastPlayedLabel;
    JLabel currentTrumpLabel;

    CardPanelSmall lastPlayedCard;
    CardPanelSmall currentTrumpCard;

    JLabel currentCategory;
    JLabel currentValue;


    public BottomPanelTopRight(Controller controller) {
        super(new BorderLayout());

        this.controller = controller;

        topTitles = new JPanel();
        centerCards = new JPanel();
        bottomDetails = new JPanel();

        lastPlayedLabel = new JLabel("last played");
        currentTrumpLabel = new JLabel("current trump");

        lastPlayedCard = new CardPanelSmall(controller.game.lastPlayedCard.getFileName(), Frame.filePrefix);
        currentTrumpCard = new CardPanelSmall(controller.game.currentTrump.getFileName(), Frame.filePrefix);

        currentCategory = new JLabel("Current category:");
        currentValue = new JLabel("Value to beat:");

        setupLabels();
        createPanels();

        setLastPlayed(controller.game.lastPlayedCard, controller.game.currentTrump);
        setTrump(controller.game.currentTrump);

        this.add(topTitles, BorderLayout.PAGE_START);
        this.add(centerCards, BorderLayout.CENTER);
        this.add(bottomDetails, BorderLayout.PAGE_END);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }



    void createTopPanel (){
        topTitles.setLayout(new BorderLayout());
        topTitles.add(lastPlayedLabel, BorderLayout.LINE_START);
        topTitles.add(currentTrumpLabel, BorderLayout.LINE_END);
    }

    void createCenterCards(){
        centerCards.setLayout(new BorderLayout());
        centerCards.add(lastPlayedCard, BorderLayout.LINE_START);
        centerCards.add(currentTrumpCard, BorderLayout.LINE_END);
    }

    void createBottomDetails(){
        bottomDetails.setLayout(new BorderLayout());
        bottomDetails.add(currentCategory, BorderLayout.PAGE_START);
        bottomDetails.add(currentValue, BorderLayout.PAGE_END);

    }

    void createPanels(){
        createTopPanel();
        createCenterCards();
        createBottomDetails();
    }

    void setupLabels(){
        currentCategory.setFont(Frame.largeFont);
        currentValue.setFont(Frame.largeFont);
        lastPlayedLabel.setFont(Frame.largeFont);
        currentTrumpLabel.setFont(Frame.largeFont);
    }



    void setLastPlayed(BaseCard card, TrumpCard trump){
        lastPlayedCard = new CardPanelSmall(card.getFileName(), Frame.filePrefix);
        if( card.getCardType().toLowerCase().contains("play")){
            currentValue.setText(((MineralCard)card).getCurrentValue(trump));
        } else {
            currentValue.setText("None");
        }
    }

    void setTrump(TrumpCard trump){
        currentTrumpCard = new CardPanelSmall(trump.getFileName(), Frame.filePrefix);
        currentCategory.setText(trump.getCurrentCategory());
    }



    public static void main(String[] args) {

        BottomPanelTopRight thisPanel = new BottomPanelTopRight(new Controller(new Game()));
        thisPanel.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(thisPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();
    }
}
