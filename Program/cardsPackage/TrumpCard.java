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

    @Override
    public String getDetails() {
        return (super.getDetails() + " " + getSubTitle());
    }
}
