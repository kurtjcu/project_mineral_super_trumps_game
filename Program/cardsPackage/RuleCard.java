package cardsPackage;

import java.util.ArrayList;

/**
 * Created by kurt on 4/09/2016.
 */
public class RuleCard extends BaseCard {
    private String subTitle;                // name of card

    public RuleCard(String fileName, String imageName, String cardType, String title, String subTitle){
        super(fileName, imageName, cardType, title);
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

}