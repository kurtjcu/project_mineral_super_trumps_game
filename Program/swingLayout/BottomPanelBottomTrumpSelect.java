package swingLayout;

import cardsPackage.BaseCard;
import cardsPackage.TrumpCard;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class BottomPanelBottomTrumpSelect extends JPanel {

    int gameHeight = (int) (Math.round(Frame.ySize * .333));
    int gameWidth = (int) (Math.round(Frame.xSize));

    Controller controller;


    JPanel cardsInHand;
    JScrollPane scrollPane;
    ArrayList<TrumpCard> hand;
    ArrayList<CardPanelNormal> handView;




    public BottomPanelBottomTrumpSelect(Controller controller) {
        super();
        this.controller = controller;
        hand = new ArrayList<>();
        handView = new ArrayList<>();
        cardsInHand = new JPanel();
        setHand(controller.game.trumpCards);
        scrollPane = new JScrollPane(cardsInHand);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.add(scrollPane);
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);
    }





    void setHand(ArrayList<TrumpCard> cards){
        //handView.clear();
        //cardsInHand.removeAll();
        hand = cards;
        for(BaseCard cardInHand : hand) {
            if(!cardInHand.getTitle().toLowerCase().contains("geologist"))
            handView.add(new CardPanelNormal(cardInHand, Frame.filePrefix));
        }
        for(CardPanelNormal cardImage : handView){
            cardImage.addActionListener(controller.playerSelectTrump);
            cardsInHand.add(cardImage);
        }
    }


    /*
    public static void main(String[] args) {

        BottomPanelBottomHandView thisPanel = new BottomPanelBottomHandView(new Controller(new Game()));
        thisPanel.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(thisPanel);

        for(int i = 0; i < 10; i++) {
            thisPanel.handView.add(new CardPanelNormal("Slide65.jpg", Frame.filePrefix));
        }

        for(CardPanelNormal cardImage : thisPanel.handView){
            thisPanel.cardsInHand.add(cardImage);
        }


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();



    }
    */
}
