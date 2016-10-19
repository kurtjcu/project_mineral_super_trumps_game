package swingLayout;

import cardsPackage.BaseCard;
import cardsPackage.MineralCard;
import cardsPackage.TrumpCard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class BottomPanelTopRight extends JPanel {

    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBag.ySize * .333));
    int gameWidth = (int) (Math.round(FrameGridBag.xSize * .333));

    String filePrefix = "Program/swingLayout/images/";

    JPanel topTitles;
    JPanel centerCards;
    JPanel bottomDetails;

    JLabel lastPlayedLabel;
    JLabel currentTrumpLabel;

    CardPanelSmall lastPlayedCard;
    CardPanelSmall currentTrumpCard;

    JLabel currentCategory;
    JLabel currentValue;


    public BottomPanelTopRight() {
        super(new BorderLayout());

        topTitles = new JPanel();
        centerCards = new JPanel();
        bottomDetails = new JPanel();

        lastPlayedLabel = new JLabel("last played");
        currentTrumpLabel = new JLabel("current trump");

        lastPlayedCard = new CardPanelSmall("Slide65.jpg", filePrefix);
        currentTrumpCard = new CardPanelSmall("Slide66.jpg", filePrefix);

        currentCategory = new JLabel("Current category:");
        currentValue = new JLabel("Value to beat:");

        setupLabels();
        createPanels();

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
        currentCategory.setFont(largeFont);
        currentValue.setFont(largeFont);
        lastPlayedLabel.setFont(largeFont);
        currentTrumpLabel.setFont(largeFont);
    }


    void setLastPlayed(BaseCard card, TrumpCard trump){
        lastPlayedCard = new CardPanelSmall(card.getFileName(), filePrefix);
        if( card.getCardType().toLowerCase().contains("play")){
            currentValue.setText(((MineralCard)card).getCurrentValue(trump));
        } else {
            currentValue.setText("None");
        }
    }

    void setTrump(TrumpCard trump){
        currentTrumpCard = new CardPanelSmall(trump.getFileName(), filePrefix);
        currentCategory.setText(trump.getCurrentCategory());
    }


    public static void main(String[] args) {

        BottomPanelTopRight thisPanel = new BottomPanelTopRight();
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