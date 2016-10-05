package cardsPackage;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by kurt on 4/09/2016.
 */
public abstract class BaseCard {
    String fileName;            // name of image file
    String imageName;            // name of image
    private String cardType;            // normal or trump?? (dont forget there is also an instructions type)
    String title;                // name of card
    Integer error = 0;           // Error if not 0



    BaseCard(String fileName, String imageName, String cardType, String title) {
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;

        if (CardStatic.cardType.contains(cardType)) {
            this.cardType = cardType;
        } else {
            errorLoadingCard("CardType not supported " + cardType);
            error = error + 1;
        }
    }

    /*** Getters ***/

    // not in use yet
    public String getFileName() {
        return fileName;
    }

    //not in use yet
    public String getImageName() {
        return imageName;
    }

    public String getCardType() {
        return cardType;

    }

    public String getTitle() {
        return title;
    }

    public Integer getError(){
        return error;
    }

    public boolean isThisCardGreaterThan(TrumpCard trump, MineralCard cardToCompare) {

        return true;
    }

    // Lets user know of error loading card
    static void errorLoadingCard(String error) {
        System.out.println("There was an error constructing card! \n it was:" + error);
    }

    public static int getCardIndexByTitle(ArrayList arrayOfCards, String titleString) {

        for (Object card : arrayOfCards) {
            if (((BaseCard)card).getTitle().toLowerCase().contains(titleString.toLowerCase()))
                return arrayOfCards.indexOf(card);
        }
        System.out.println("didn't find card in getCardIndexByTitle");
        return -1;
    }

    public static ArrayList<BaseCard> getCardsAsBase(ArrayList list){
        ArrayList<BaseCard> baseCardList = new ArrayList<>();
        for(Object card : list){
            baseCardList.add((BaseCard)card);
        }
        return baseCardList;
    }

    @Override
    public String toString(){
        return title;
    }

}
