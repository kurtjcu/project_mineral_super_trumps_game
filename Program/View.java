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

    String menuTop = String.format("       | %-20s | %-11s | %-11s | %-11s | %-20s | %-12s | %-10s |%n", "Name",
            "Card Type", "Hardness", "Spec Grav", "Cleavage", "Crustal Abun", "Value");

    public void showString (String string){
        System.out.println(string);
    }

    public String getFormattedCardString(BaseCard card){
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


    //TODO:not used??
    public String showHand (ArrayList<BaseCard> hand){
        return showHand(hand, 0);
    }

    public String showHand (ArrayList<BaseCard> hand, int offset){
        StringBuilder string = new StringBuilder();
        int i = 0;
        for(; i < hand.size(); i++){
            string = string.append(String.format(" %-3d : %s%n", i + offset, getFormattedCardString(hand.get(i))));
        }
        return string.toString();
    }

    //show all cards in hand and return what the user types in.
    public String showCardSelection(ArrayList<BaseCard> cardsList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select a card ");
        System.out.printf(menuTop);
        System.out.println(showHand(cardsList, 1));
        return scanner.next();
    }

    public String showCardSelectionWithPass(ArrayList<BaseCard> cardsList){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Select a card ");
        System.out.printf(menuTop);
        System.out.printf(" %-3d : | %-113s |%n", 0, "pass and pickup a card");
        System.out.println(showHand(cardsList, 1));
        return scanner.next();
    }


    public String showCardSelectionWithPassAndMessage(ArrayList<BaseCard> cardsList, String error){
        System.out.println(error);
        return showCardSelectionWithPass(cardsList);
    }

    public String showCardSelectionWithMessage(ArrayList<BaseCard> cardsList, String error){
        System.out.println(error);
        return showCardSelection(cardsList);
    }


    public void showCard(BaseCard card){
        System.out.printf(" %-3d : %s%n", 0, getFormattedCardString(card));
    }

    public void showCard(BaseCard card, int offset){
        System.out.printf(" %-3d : %s%n", offset, getFormattedCardString(card));
    }

    public void showCardWithMessage(BaseCard card, String message){
        if(!message.equals("")){
            System.out.println(message);
        }
        System.out.printf(menuTop);
        System.out.printf(" %-3d : %s%n", 0, getFormattedCardString(card));
    }

    public void pauseForEnter(String displayString){
        Scanner scanner = new Scanner(System.in);
        if(displayString.equals("")){
            System.out.println("Press a key then \"Enter\" to continue");
        } else {
            System.out.println(displayString);
        }
        scanner.hasNext();
    }
}
