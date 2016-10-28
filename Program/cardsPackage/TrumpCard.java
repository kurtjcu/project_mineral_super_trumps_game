package cardsPackage;

/**
 * Created by kurt on 4/09/2016.
 */
public class TrumpCard extends BaseCard {
    private String subTitle;                // name of card

    public TrumpCard(String fileName, String imageName, String cardType, String title, String subTitle) {
        super(fileName, imageName, cardType, title);
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getCurrentCategory(){
        switch (getTitle()) {

            case "The Miner":
                return("Economic Value");

            case "The Petrologist":
                return ("Crustal Abundance");

            case "The Gemmologist":
                return ("Hardness");

            case "The Mineralogist":
                return ("Cleavage");

            case "The Geophysicist":
                return ("Specific Gravity");

            case "The Geologist":
                return ("None for the geologist");

            default:
                System.out.println("ERROR - Could not find Value for current trump");
                break;
        }
        return "none";
    }
}
