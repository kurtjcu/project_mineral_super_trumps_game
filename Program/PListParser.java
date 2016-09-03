/**
 * Created by kurt on 1/09/2016.
 */

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class PListParser {

    public static void main(String[] args) {
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
                System.out.println("\nDict Element :"
                        + nNode.getNodeName());

                //get cards from root element
                NodeList nChildNodes = nNode.getChildNodes();
                System.out.println(nChildNodes.getLength());

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
                        System.out.println("its a normal card");

                        NodeList nArrayNodes = eNormalCard.getElementsByTagName("array");
                        if (nArrayNodes.item(0).getNodeType() == Node.ELEMENT_NODE) {
                            Element eOccurrence = (Element) nArrayNodes.item(0);
                            NodeList nOccurrence = eOccurrence.getElementsByTagName("string");
                            for (int j = 0; j < nOccurrence.getLength(); j++) {
                                aOccurrenceArrayItems.add(nOccurrence.item(j).getTextContent());
                            }

                        }
                        //TODO: create card and store in collection

                        //print to console for testing
                        aWhatIWillMakeCardFrom.add(aStringItems.get(0));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(1));
                        aWhatIWillMakeCardFrom.add(aKeyItems.get(3));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(2));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(3));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(4));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(5));
                        aWhatIWillMakeCardFrom.add(aOccurrenceArrayItems);
                        aWhatIWillMakeCardFrom.add(aStringItems.get(6));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(7));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(8));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(9));
                        aWhatIWillMakeCardFrom.add(aStringItems.get(10));
                        for (Object item : aWhatIWillMakeCardFrom) {
                            System.out.println(item);
                        }
                    }

                    if (nChildNodes.getLength() == 21) {
                        //System.out.println("its a trump or rules card");
                        if (aKeyItems.get(3).toLowerCase().contains("trump")) {
                            System.out.println("its a trump card");


                        } else if (aKeyItems.get(3).toLowerCase().contains("rule")){
                            System.out.println("its a rules card");
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
