package cardsPackage;

/**
 * Created by kurt on 4/09/2016.
 */
public class BaseCard {
    protected String fileName;            // name of image file
    protected String imageName;            // name of image
    protected String cardType;            // normal or trump?? (dont forget there is also an instructions type)
    protected String title;                // name of card
    protected Integer error = 0;           // Error if not 0



    public BaseCard(String fileName, String imageName, String cardType, String title) {
        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;

        if (CardStatic.alCardType.contains(cardType)) {
            this.cardType = cardType;
        } else {
            errorLoadingCard("CardType not supported " + cardType);
            error = error + 1;
        }
    }

    /*** Getters ***/
    public String getFileName() {
        return fileName;
    }

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

    // Lets user know of error loading card
   protected static void errorLoadingCard(String error) {
        System.out.println("There was an error constructing card! \n it was:" + error);
    }

}
