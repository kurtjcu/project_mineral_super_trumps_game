import cardsPackage.BaseCard;
import cardsPackage.MineralCard;
import cardsPackage.RuleCard;
import cardsPackage.TrumpCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by kurt.Schoenhoff on 18/09/2016.
 */
public class View {

    public static void showString (String string){
        System.out.println(string);
    }

    public static String getFormattedCardString(BaseCard card){
        String cardType = card.getCardType().toLowerCase();
        String string;
        String stringPrefix = String.format("| %-20s | %-11s |",card.getTitle(), card.getCardType());
        switch (cardType) {
            case "play":
                MineralCard mineralCard = (MineralCard)card;
                string = String.format(" %-11s | %-11s | %-20s | %-12s | %-10s |",
                        Arrays.toString( mineralCard.getHardness()),
                        Arrays.toString(mineralCard.getSpecificGravity()),
                        mineralCard.getCleavage(),
                        mineralCard.getCrustalAbundance(),
                        mineralCard.getEconomicValue())
                        ;
                break;

            case "trump" :
                TrumpCard trumpCard = (TrumpCard) card;
                string = "\u001B[31m" + String.format(" %-76s |",trumpCard.getSubTitle()) + "\u001B[0m";
                break;

            case "rule" :
                RuleCard ruleCard = (RuleCard)card;
                string = String.format(" %-76s |",ruleCard.getSubTitle()) ;
                break;

            default:
                string = "Unknown card type";
        }
        return(stringPrefix + string);
    }

    public static String showHand (ArrayList<BaseCard> hand){
        return showHand(hand, 0);
    }
    public static String showHand (ArrayList<BaseCard> hand, int offset){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < hand.size() + offset; i++){
            string = string.append(String.format(" %-3d : %s%n", i, getFormattedCardString(hand.get(i - offset))));
        }
        return string.toString();
    }

    //show all cards in hand and return what the user types in.
    public static String getCardSelection(ArrayList<BaseCard> cardsList){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavage", "Crustal Abun", "Value");
        System.out.println(showHand(cardsList, 1));
        return scanner.next();
    }

    public static String getCardSelectionWithPass(ArrayList<BaseCard> cardsList){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavage", "Crustal Abun", "Value");
        System.out.printf(" %-3d : | %-113s |%n", 0, "pass and pickup a card");
        System.out.println(showHand(cardsList, 1));
        return scanner.next();
    }
    public static String getCardSelectionWithPassAndError(ArrayList<BaseCard> cardsList, String error){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name", "Card Type", "Hardness", "Spec Grav", "Cleavage", "Crustal Abun", "Value");
        System.out.printf(" %-3d : | %-113s |%n", 0, "pass and pickup a card");
        System.out.println(showHand(cardsList, 1));
        return scanner.next();
    }
}
