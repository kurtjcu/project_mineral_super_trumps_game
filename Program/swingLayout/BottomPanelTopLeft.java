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
public class BottomPanelTopLeft extends JPanel {

    Controller controller;
    int gameHeight = (int) (Math.round(Frame.ySize * .333));
    int gameWidth = (int) (Math.round(Frame.xSize * .666));

    JPanel leftButtons;
    JPanel centerCurrentSelectedCard;
    JPanel rightDetails;

    JLabel detailsHardness;
    JLabel detailsSpecificGravity;
    JLabel detailsCleavage;
    JLabel detailsCrustalAbundance;
    JLabel detailsEconomicValue;

    BaseCard selectedCard;
    CardPanelNormal selectedCardImage;

    JButton pass;
    JButton playCard;


    public BottomPanelTopLeft(Controller controller) {
        super(new BorderLayout());
        this.controller = controller;
        leftButtons = new JPanel();
        centerCurrentSelectedCard = new JPanel();
        rightDetails = new JPanel();

        selectedCard = controller.game.currentPlayer.getHand().get(0);
        controller.game.currentlySelectedCard = selectedCard;
        //System.out.println(selectedCard.getTitle());



        detailsHardness = new JLabel("Hardness: " );
        detailsSpecificGravity = new JLabel("Specific Gravity: ");
        detailsCleavage = new JLabel("Cleavage: ");
        detailsCrustalAbundance = new JLabel("Crustal Abundance: ");
        detailsEconomicValue = new JLabel("Economic Value: ");

        setSelectedCardImage(selectedCard);
        selectedCardImage = new CardPanelNormal(selectedCard, Frame.filePrefix);

        pass = new JButton("Pass");
        pass.addActionListener(controller.playerPass);
        playCard = new JButton("Play Card");
        playCard.addActionListener(controller.playerPlayCard);

        setupLabels();
        createPanels();

        this.add(leftButtons, BorderLayout.LINE_START);
        this.add(centerCurrentSelectedCard, BorderLayout.CENTER);
        this.add(rightDetails, BorderLayout.LINE_END);

        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);

    }

    public BottomPanelTopLeft(Controller controller, BaseCard card) {
        this(controller);
        selectedCard = card;
        controller.game.currentlySelectedCard = card;
        setSelectedCardImage(card);
        //selectedCardImage = new CardPanelNormal(selectedCard, Frame.filePrefix);
        setupLabels();
        createPanels();
        this.removeAll();
        this.add(leftButtons, BorderLayout.LINE_START);
        this.add(centerCurrentSelectedCard, BorderLayout.CENTER);
        this.add(rightDetails, BorderLayout.LINE_END);

    }




    void createLeftButtons(){
        leftButtons.setLayout(new BorderLayout());
        leftButtons.add(pass, BorderLayout.PAGE_START);
        leftButtons.add(playCard, BorderLayout.PAGE_END);
    }

    void createCenterCurrentSelectedCard(){
        centerCurrentSelectedCard = new JPanel();
        centerCurrentSelectedCard.setLayout(new BorderLayout());
        centerCurrentSelectedCard.add(selectedCardImage, BorderLayout.CENTER);
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
        pass.setFont(Frame.largeFont);
        playCard.setFont(Frame.largeFont);
        detailsHardness.setFont(Frame.largeFont);
        detailsSpecificGravity.setFont(Frame.largeFont);
        detailsCleavage.setFont(Frame.largeFont);
        detailsCrustalAbundance.setFont(Frame.largeFont);
        detailsEconomicValue.setFont(Frame.largeFont);
    }



    void setSelectedCardImage(BaseCard card){
        //selectedCardImage = new CardPanelNormal(card, Frame.filePrefix);
        controller.game.currentlySelectedCard = card;
        if( card.getCardType().toLowerCase().contains("play")){
            MineralCard currentCard = (MineralCard)card;
            detailsHardness.setText("Hardness: " + currentCard.getHardness()[0]);
            detailsSpecificGravity.setText("Specific Gravity: " + currentCard.getSpecificGravity()[0]);
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
        selectedCardImage = new CardPanelNormal(card, Frame.filePrefix);
    }




    public static void main(String[] args) {

        BottomPanelTopLeft thisPanel = new BottomPanelTopLeft(new Controller(new Game()));
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
