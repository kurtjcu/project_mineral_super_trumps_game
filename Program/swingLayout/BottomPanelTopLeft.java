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
public class BottomPanelTopLeft extends JPanel {

    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .333));
    int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize * .666));

    String filePrefix = "Program/swingLayout/images/";

    JPanel leftButtons;
    JPanel centerCurrentSelectedCard;
    JPanel rightDetails;

    JLabel detailsHardness;
    JLabel detailsSpecificGravity;
    JLabel detailsCleavage;
    JLabel detailsCrustalAbundance;
    JLabel detailsEconomicValue;

    CardPanelNormal selectedCard;

    JButton pass;
    JButton playCard;


    public BottomPanelTopLeft() {
        super(new BorderLayout());

        leftButtons = new JPanel();
        centerCurrentSelectedCard = new JPanel();
        rightDetails = new JPanel();

        detailsHardness = new JLabel("last played");
        detailsSpecificGravity = new JLabel("current trump");
        detailsCleavage = new JLabel("current trump");
        detailsCrustalAbundance = new JLabel("current trump");
        detailsEconomicValue = new JLabel("current trump");

        selectedCard = new CardPanelNormal("Slide65.jpg", filePrefix);

        pass = new JButton("Pass");
        playCard = new JButton("Play Card");

        setupLabels();
        createPanels();

        this.add(leftButtons, BorderLayout.LINE_START);
        this.add(centerCurrentSelectedCard, BorderLayout.CENTER);
        this.add(rightDetails, BorderLayout.LINE_END);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }
    
    void createLeftButtons(){
        leftButtons.setLayout(new BorderLayout());
        leftButtons.add(pass, BorderLayout.PAGE_START);
        leftButtons.add(playCard, BorderLayout.PAGE_END);
    }
    
    void createCenterCurrentSelectedCard(){
        centerCurrentSelectedCard.setLayout(new BorderLayout());
        centerCurrentSelectedCard.add(selectedCard, BorderLayout.CENTER);
    }
    
    void createRightDetails(){
        rightDetails.setLayout(new BoxLayout(rightDetails, BoxLayout.Y_AXIS));
        rightDetails.add(detailsHardness);
        rightDetails.add(detailsSpecificGravity);
        rightDetails.add(detailsCleavage);
        rightDetails.add(detailsCrustalAbundance);
        rightDetails.add(detailsEconomicValue);

    }
    
    void createPanels(){
        createLeftButtons();
        createCenterCurrentSelectedCard();
        createRightDetails();
    }
    
    void setupLabels(){
        pass.setFont(largeFont);
        playCard.setFont(largeFont);
        detailsHardness.setFont(largeFont);
        detailsSpecificGravity.setFont(largeFont);
        detailsCleavage.setFont(largeFont);
        detailsCrustalAbundance.setFont(largeFont);
        detailsEconomicValue.setFont(largeFont);
    }


    void setSelectedCard(BaseCard card){
        selectedCard = new CardPanelNormal(card.getFileName(), filePrefix);
        if( card.getCardType().toLowerCase().contains("play")){
            MineralCard currentCard = (MineralCard)card;
            detailsHardness.setText("Hardness: " + currentCard.getHardness().toString());
            detailsSpecificGravity.setText("Specific Gravity: " + currentCard.getSpecificGravity().toString());
            detailsCleavage.setText("Cleavage: " + currentCard.getCleavage());
            detailsCrustalAbundance.setText("Crustal Abundance: " + currentCard.getCrustalAbundance());
            detailsEconomicValue.setText("Economic Value: " + currentCard.getEconomicValue());
        } else if (card.getCardType().toLowerCase().contains("trump")){
            TrumpCard currentCard = (TrumpCard) card;
            detailsHardness.setText("Trump Category:");
            detailsSpecificGravity.setText(currentCard.getCurrentCategory());
            detailsCleavage.setText("");
            detailsCrustalAbundance.setText("");
            detailsEconomicValue.setText("");
        }

        selectedCard = new CardPanelNormal(card.getFileName(), filePrefix);
    }



    public static void main(String[] args) {

        BottomPanelTopLeft thisPanel = new BottomPanelTopLeft();
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
