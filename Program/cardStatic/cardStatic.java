package cardStatic;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by kurt on 30/08/2016.
 */
public class cardStatic {


    public static final ArrayList alCardType;
    static {
        ArrayList<String> tempArrayList= new ArrayList<String>();
        tempArrayList.add("play");
        tempArrayList.add("trump");
        tempArrayList.add("rule");
        alCardType = tempArrayList;


    }

    public static final Dictionary<String, Integer> dCardType;
    static {
        Hashtable<String, Integer> tempHashTable = new Hashtable<String, Integer>();
        tempHashTable.put("ultratrace", 0);
        tempHashTable.put("trace", 1);
        tempHashTable.put("low", 2);
        tempHashTable.put("moderate", 3);
        tempHashTable.put("high", 4);
        tempHashTable.put("veryhigh", 5);
        dCardType = tempHashTable;

    }

    public enum eCrystalSystem {
    }

    public static final Dictionary<String, Integer> dCrustalAbundance;
    static {
        Hashtable<String, Integer> tempHashTable = new Hashtable<String, Integer>();
        tempHashTable.put("ultratrace", 0);
        tempHashTable.put("trace", 1);
        tempHashTable.put("low", 2);
        tempHashTable.put("moderate", 3);
        tempHashTable.put("high", 4);
        tempHashTable.put("veryhigh", 5);
        dCrustalAbundance = tempHashTable;

    }

    public static final Dictionary<String, Integer> dCleavage;
    static {
        Hashtable<String, Integer> tempHashTable = new Hashtable<String, Integer>();
        tempHashTable.put("none", 0);
        tempHashTable.put("poor/none", 1);
        tempHashTable.put("1 poor", 2);
        tempHashTable.put("2 poor", 3);
        tempHashTable.put("1 good", 4);
        tempHashTable.put("1 good, 1 poor", 5);
        tempHashTable.put("2 good", 6);
        tempHashTable.put("3 good", 7);
        tempHashTable.put("1 perfect", 8);
        tempHashTable.put("1 perfect, 1 good", 9);
        tempHashTable.put("1 perfect, 2 good", 10);
        tempHashTable.put("2 perfect, 1 good", 11);
        tempHashTable.put("3 perfect", 12);
        tempHashTable.put("4 perfect", 13);
        tempHashTable.put("6 perfect", 14);
        dCleavage = tempHashTable;

    }

    public static final Dictionary<String, Integer> dEconomicValue;
    static {
        Hashtable<String, Integer> tempHashTable = new Hashtable<String, Integer>();
        tempHashTable.put("trivial", 0);
        tempHashTable.put("low", 1);
        tempHashTable.put("moderate", 2);
        tempHashTable.put("high", 3);
        tempHashTable.put("very high", 4);
        tempHashTable.put("I'm rich!", 5);
        dEconomicValue = tempHashTable;

    }

}
