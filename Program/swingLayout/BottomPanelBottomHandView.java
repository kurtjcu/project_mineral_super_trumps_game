package swingLayout;

import cardsPackage.BaseCard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by kurt.Schoenhoff on 18/10/2016.
 */
public class BottomPanelBottomHandView extends JPanel {

    public Font largeFont = new Font("SansSerif", Font.BOLD, 20);

    int gameHeight = (int) (Math.round(FrameGridBagMainGame.ySize * .333));
    int gameWidth = (int) (Math.round(FrameGridBagMainGame.xSize));

    String filePrefix = "Program/swingLayout/images/";

    JPanel cardsInHand;
    JScrollPane scrollPane;
    ArrayList<BaseCard> hand;
    ArrayList<CardPanelNormal> handView;




    public BottomPanelBottomHandView() {
        super();
        hand = new ArrayList<>();
        handView = new ArrayList<>();
        cardsInHand = new JPanel();
        scrollPane = new JScrollPane(cardsInHand);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.add(scrollPane);
        this.setBorder(new LineBorder(Color.GREEN, 2));
        this.setVisible(true);
    }


    void setHand(ArrayList<BaseCard> cards){
        handView.clear();
        cardsInHand.removeAll();
        for(BaseCard cardInHand : hand) {
            handView.add(new CardPanelNormal(cardInHand.getFileName(), filePrefix));
        }
        for(CardPanelNormal cardImage : handView){
            cardsInHand.add(cardImage);
        }
    }


    public static void main(String[] args) {

        BottomPanelBottomHandView thisPanel = new BottomPanelBottomHandView();
        thisPanel.setBorder(new LineBorder(Color.GREEN, 2));

        JFrame frame = new JFrame("Testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(thisPanel);

        for(int i = 0; i < 10; i++) {
            thisPanel.handView.add(new CardPanelNormal("Slide65.jpg", thisPanel.filePrefix));
        }

        for(CardPanelNormal cardImage : thisPanel.handView){
            thisPanel.cardsInHand.add(cardImage);
        }


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.pack();



    }
}