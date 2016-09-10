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


    public static ArrayList getCardsList(){
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
                //System.out.println("\nDict Element :"
                //+ nNode.getNodeName());

                //get cards from root element
                NodeList nChildNodes = nNode.getChildNodes();
                //System.out.println(nChildNodes.getLength());

                ArrayList<String> aKeyItems = new ArrayList<String>();
                ArrayList<String> aStringItems = new ArrayList<String>();
                ArrayList<String> aOccurrenceArrayItems = new ArrayList<String>();

                ArrayList<Object> aWhatIWillMakeCardFrom = new ArrayList<>();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eNormalCard = (Element) nNode;

                    //get all the keys
                    NodeList nKeyNodes = eNormalCard.getElementsByTagName("key");
                    for (int j = 0; j < nKeyNodes.getLength(); j++) {
                        aKeyItems.add(nKeyNodes.item(j).getTextContent());
                    }

                    //get all the strings
                    NodeList nStringNodes = eNormalCard.getElementsByTagName("string");
                    for (int j = 0; j < nStringNodes.getLength(); j++) {
                        if (!nStringNodes.item(j).getParentNode().getNodeName().equals("array")) {  ///make sure it is not the strings in the array
                            aStringItems.add(nStringNodes.item(j).getTextContent());
                        }
                    }


                    //check for playing card
                    if (nChildNodes.getLength() == 53) {
                        //System.out.println("its a normal card");

                        NodeList nArrayNodes = eNormalCard.getElementsByTagName("array");
                        if (nArrayNodes.item(0).getNodeType() == Node.ELEMENT_NODE) {
                            Element eOccurrence = (Element) nArrayNodes.item(0);
                            NodeList nOccurrence = eOccurrence.getElementsByTagName("string");
                            for (int j = 0; j < nOccurrence.getLength(); j++) {
                                aOccurrenceArrayItems.add(nOccurrence.item(j).getTextContent());
                            }

                        }

                        String[] aOccurrence = new String[aOccurrenceArrayItems.size()];
                        for(int i = 0; i <= aOccurrenceArrayItems.size()-1; i++ ) {
                            aOccurrence[i] = aOccurrenceArrayItems.get(i);
                        }

                        String[] sHardness = aStringItems.get(6).split("-");
                        if (sHardness.length < 2 ){
                            sHardness = aStringItems.get(6).split(" ");
                        }

                        Double[] dHardness = new Double[sHardness.length];
                        for(int i = 0; i <= sHardness.length-1; i++ ) {
                            dHardness[i] = Double.parseDouble(sHardness[i]);
                        }

                        String[] sSpecificGravity = aStringItems.get(7).split("-");
                        Double[] dSpecificGravity = new Double[sSpecificGravity.length];
                        for(int i = 0; i <= sSpecificGravity.length-1; i++ ) {
                            dSpecificGravity[i] = Double.parseDouble(sSpecificGravity[i]);
                        }

                        MineralCard newMineralCard = new MineralCard(aStringItems.get(0),
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
                                aStringItems.get(10) );

                        cardList.add(newMineralCard);
                    }

                    if (nChildNodes.getLength() == 21) {
                        //System.out.println("its a trump or rules card");
                        if (aKeyItems.get(3).toLowerCase().contains("trump")) {
                            //System.out.println("its a trump card");

                            TrumpCard trumpCard = new TrumpCard(aStringItems.get(0),
                                    aStringItems.get(1),
                                    aKeyItems.get(3),
                                    aStringItems.get(2),
                                    aStringItems.get(3));

                            cardList.add(trumpCard);


                        } else if (aKeyItems.get(3).toLowerCase().contains("rule")){
                            //System.out.println("its a rules card");

                            RuleCard ruleCard = new RuleCard(aStringItems.get(0),
                                    aStringItems.get(1),
                                    aKeyItems.get(3),
                                    aStringItems.get(2),
                                    aStringItems.get(3));

                            cardList.add(ruleCard);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  cardList;
    }

    public static void main(String[] args) {



        try {


            ArrayList<BaseCard> cardList = PListParser.getCardsList();

            for(BaseCard card : cardList) {

                System.out.println("Card Title is " + card.getTitle());
                if(card.getError() > 0){
                    System.out.println("There was at least one error with this card: " + card.getTitle() + " \n ErrorCount = " +card.getError());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
