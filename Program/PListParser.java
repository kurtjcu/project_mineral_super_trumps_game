/**
 * Created by kurt on 1/09/2016.
 */

import cardsPackage.*;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class PListParser {

    private static ArrayList<String> aKeyItems = new ArrayList<>();
    private static ArrayList<String> aStringItems = new ArrayList<>();
    private static ArrayList<String> aOccurrenceArrayItems = new ArrayList<>();
    private static Element eNormalCard;


    private static ArrayList<String> elementsToArray(Element element, String elementName) {
        ArrayList<String> tempArrayList = new ArrayList<>();
        NodeList nodes = element.getElementsByTagName(elementName);
        for (int j = 0; j < nodes.getLength(); j++) {
            if (!nodes.item(j).getParentNode().getNodeName().equals("array")) {  ///make sure it is not the strings in the array
                tempArrayList.add(nodes.item(j).getTextContent());
            }
        }
        return tempArrayList;
    }

    private static BaseCard extractTrumpOrRulesCard() {
        if (aKeyItems.get(3).toLowerCase().contains("trump")) {
            //System.out.println("its a trump card");

            return (new TrumpCard(aStringItems.get(0),
                    aStringItems.get(1),
                    aKeyItems.get(3),
                    aStringItems.get(2),
                    aStringItems.get(3)));

        } else {
            //System.out.println("its a rules card");

            return (new RuleCard(aStringItems.get(0),
                    aStringItems.get(1),
                    aKeyItems.get(3),
                    aStringItems.get(2),
                    aStringItems.get(3)));
        }

    }

    private static BaseCard extractMineralCard() {

        NodeList nArrayNodes = eNormalCard.getElementsByTagName("array");
        if (nArrayNodes.item(0).getNodeType() == Node.ELEMENT_NODE) {
            Element eOccurrence = (Element) nArrayNodes.item(0);
            aOccurrenceArrayItems = elementsToArray(eOccurrence, "string");
        }

        String[] aOccurrence = new String[aOccurrenceArrayItems.size()];
        for (int i = 0; i <= aOccurrenceArrayItems.size() - 1; i++) {
            aOccurrence[i] = aOccurrenceArrayItems.get(i);
        }

        String[] sHardness = aStringItems.get(6).split("-");
        if (sHardness.length < 2) {
            sHardness = aStringItems.get(6).split(" ");
        }

        Double[] dHardness = new Double[sHardness.length];
        for (int i = 0; i <= sHardness.length - 1; i++) {
            dHardness[i] = Double.parseDouble(sHardness[i]);
        }

        String[] sSpecificGravity = aStringItems.get(7).split("-");
        Double[] dSpecificGravity = new Double[sSpecificGravity.length];
        for (int i = 0; i <= sSpecificGravity.length - 1; i++) {
            dSpecificGravity[i] = Double.parseDouble(sSpecificGravity[i]);
        }

        return new MineralCard(aStringItems.get(0),
                aStringItems.get(1),
                aKeyItems.get(3),
                aStringItems.get(2),
                aStringItems.get(3),
                aStringItems.get(4),
                aStringItems.get(5),
                aOccurrence,
                dHardness,
                dSpecificGravity,
                aStringItems.get(8),
                aStringItems.get(9).trim(),
                aStringItems.get(10));
    }

    static ArrayList<BaseCard> getCardsList() {
        ArrayList<BaseCard> cardList = new ArrayList<>();

        try {
            File inputFile = new File("MstCards_151021.plist");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            //get each card back from the plist
            NodeList nList = doc.getElementsByTagName("dict");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                NodeList nChildNodes = nNode.getChildNodes();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    eNormalCard = (Element) nNode;

                    aKeyItems = elementsToArray(eNormalCard, "key");
                    aStringItems = elementsToArray(eNormalCard, "string");
                    //check for playing card
                    if (nChildNodes.getLength() == 53) {
                        cardList.add(extractMineralCard());
                    }
                    //Check for rules or trump card
                    if (nChildNodes.getLength() == 21) {
                        //System.out.println("its a trump or rules card");
                        cardList.add(extractTrumpOrRulesCard());
                    }
                }
            }
        } catch (Exception e) {
            //TODO: print the error to trace i.e. could not reads plist file...
            e.printStackTrace();
        }
        return cardList;
    }

    public static void main(String[] args) {
        try {
            ArrayList<BaseCard> cardList = PListParser.getCardsList();

            for (BaseCard card : cardList) {

                System.out.println("Card Title is " + card.getTitle());
                if (card.getError() > 0) {
                    System.out.println("There was at least one error with this card: " + card.getTitle() + " \n ErrorCount = " + card.getError());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
