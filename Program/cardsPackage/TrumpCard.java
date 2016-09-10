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

    private String getSubTitle() {
        return subTitle;
    }

    @Override
    public String getDetails() {

        String string = String.format(" %-76s |",getSubTitle());

        return ("\u001B[31m" + super.getDetails() + string + "\u001B[0m");
    }
}
