package gamePackage;/*
 * Created by kurt on 1/09/2016.
 *
 * Does all the heavy lifting as far as getting the cards from the plist file
 */

import cardsPackage.BaseCard;
import cardsPackage.MineralCard;
import cardsPackage.RuleCard;
import cardsPackage.TrumpCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class PListParser {

    private static ArrayList<String> keyItemsFromElement = new ArrayList<>();
    private static ArrayList<String> stringItemsFomElement = new ArrayList<>();
    private static ArrayList<String> occurrenceArrayItems = new ArrayList<>();
    private static Element normalCardElement;


    public static ArrayList<String> elementsToArray(Element element, String elementName) {
        ArrayList<String> tempArrayList = new ArrayList<>();
        NodeList nodes = element.getElementsByTagName(elementName);
        for (int j = 0; j < nodes.getLength(); j++) {
            if (!nodes.item(j).getParentNode().getNodeName().equals("array")) {  ///make sure it is not the strings in the array
                tempArrayList.add(nodes.item(j).getTextContent());
            }
        }
        return tempArrayList;
    }

    public static BaseCard extractTrumpOrRulesCard() {
        if (keyItemsFromElement.get(3).toLowerCase().contains("trump")) {
            //System.out.println("its a trump card");

            return (new TrumpCard(stringItemsFomElement.get(0),
                    stringItemsFomElement.get(1),
                    keyItemsFromElement.get(3),
                    stringItemsFomElement.get(2),
                    stringItemsFomElement.get(3)));

        } else {
            //System.out.println("its a rules card");

            return (new RuleCard(stringItemsFomElement.get(0),
                    stringItemsFomElement.get(1),
                    keyItemsFromElement.get(3),
                    stringItemsFomElement.get(2),
                    stringItemsFomElement.get(3)));
        }

    }

    public static BaseCard extractMineralCard() {

        NodeList nArrayNodes = normalCardElement.getElementsByTagName("array");
        if (nArrayNodes.item(0).getNodeType() == Node.ELEMENT_NODE) {
            Element occurrenceAsElement = (Element) nArrayNodes.item(0);
            occurrenceArrayItems = elementsToArray(occurrenceAsElement, "string");
        }

        String[] occurrenceAsString = new String[occurrenceArrayItems.size()];
        for (int i = 0; i <= occurrenceArrayItems.size() - 1; i++) {
            occurrenceAsString[i] = occurrenceArrayItems.get(i);
        }

        String[] hardnessAsString = stringItemsFomElement.get(6).split("-");
        if (hardnessAsString.length < 2) {
            hardnessAsString = stringItemsFomElement.get(6).split(" ");
        }

        Double[] hardness = new Double[hardnessAsString.length];
        for (int i = 0; i <= hardnessAsString.length - 1; i++) {
            hardness[i] = Double.parseDouble(hardnessAsString[i]);
        }

        String[] specificGravity = stringItemsFomElement.get(7).split("-");
        Double[] dSpecificGravity = new Double[specificGravity.length];
        for (int i = 0; i <= specificGravity.length - 1; i++) {
            dSpecificGravity[i] = Double.parseDouble(specificGravity[i]);
        }

        return new MineralCard(stringItemsFomElement.get(0),
                stringItemsFomElement.get(1),
                keyItemsFromElement.get(3),
                stringItemsFomElement.get(2),
                stringItemsFomElement.get(3),
                stringItemsFomElement.get(4),
                stringItemsFomElement.get(5),
                occurrenceAsString,
                hardness,
                dSpecificGravity,
                stringItemsFomElement.get(8),
                stringItemsFomElement.get(9).trim(),
                stringItemsFomElement.get(10));
    }

    //reads file
    public static ArrayList<BaseCard> getCardsList() {
        ArrayList<BaseCard> cardList = new ArrayList<>();

        try {
            File inputFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory documentBuilderFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //get each card back from the plist
            NodeList nodeList = doc.getElementsByTagName("dict");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);

                NodeList childNodes = node.getChildNodes();

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    normalCardElement = (Element) node;

                    keyItemsFromElement = elementsToArray(normalCardElement, "key");
                    stringItemsFomElement = elementsToArray(normalCardElement, "string");
                    //check for playing card
                    if (childNodes.getLength() == 53) {
                        cardList.add(extractMineralCard());
                    }
                    //Check for rules or trump card
                    if (childNodes.getLength() == 21) {
                        //System.out.println("its a trump or rules card");
                        cardList.add(extractTrumpOrRulesCard());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardList;
    }

    //just for testing
    public static void main(String[] args) {
        try {
            ArrayList<BaseCard> cardList = PListParser.getCardsList();

            for (BaseCard card : cardList) {
                System.out.println("Card Title is " + card.getTitle());
                if (card.getError() > 0) {
                    System.out.println("There was at least one error with this card: " + card.getTitle() +
                            " \n ErrorCount = " + card.getError());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
