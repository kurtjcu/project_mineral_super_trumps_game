package cardsPackage;


import java.util.Arrays;

/**
 * Created by kurt on 16/08/2016.
 */
public class MineralCard extends BaseCard {


    private String chemistry;            // chemistry of mineral
    private String classification;        // classification of mineral??
    private String crystalSystem;        //enum? is this used??
    private String[] occurrence;            // need to contain types of rock it can be found in?? (Dictionary??)
    private Double[] hardness;            // can this be an int??
    private Double[] specificGravity;        // how heavy is it?
    private String cleavage;            // need to separate this into its components...
    private String crustalAbundance;    //change to enum??
    private String economicValue;        //change to enum??


    /*** Constructors ***/
    //
    public MineralCard(String fileName, String imageName, String cardType, String title,
                       String chemistry, String classification, String crystalSystem,
                       String[] occurrence, Double[] hardness, Double[] specificGravity,
                       String cleavage, String crustalAbundance, String economicValue) {

        super(fileName, imageName, cardType, title);

        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrence = occurrence;           //TODO: need to get possible values for this and run a check for each value...
        Arrays.sort(hardness);
        this.hardness = hardness;
        Arrays.sort(specificGravity);
        this.specificGravity = specificGravity;


        if (CardStatic.cleavage.get(cleavage) != null) {
            this.cleavage = cleavage;
        } else {
            errorLoadingCard("Cleavage not supported " + cleavage);
            error = error + 1;
        }

        if (CardStatic.crustalAbundance.get(crustalAbundance.replaceAll("\\s","")) != null) {
            this.crustalAbundance = crustalAbundance.trim();
        } else {
            errorLoadingCard("crustalAbundance not supported " + crustalAbundance);
            error = error + 1;
        }

        if (CardStatic.economicValue.get(economicValue) != null) {
            this.economicValue = economicValue;
        } else {
            errorLoadingCard("economicValue not supported " + economicValue);
            error = error + 1;
        }


    }


    /*** Setters  ***/

    // should be none as a card is read only once created.


    //region Getters

    /*** Getters ***/

    public String getChemistry() {
        return chemistry;
    }

    public String getClassification() {
        return classification;
    }

    public String getCrystalSystem() {
        return crystalSystem;
    }

    public String[] getOccurrence() {
        return occurrence;
    }

    public Double[] getHardness() {
        return hardness;
    }


    public Double[] getSpecificGravity() {
        return specificGravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrustalAbundance() {
        return crustalAbundance;
    }

    public String getEconomicValue() {
        return economicValue;
    }
    //endregion

    //region Helpers

    //used to see if it is a valid play
    @Override
    public boolean isThisCardGreaterThan(TrumpCard trump, MineralCard cardToCompare) {

        boolean testIs = false;
        switch(trump.getTitle()) {

            case "Economic value":
                testIs = (CardStatic.economicValue.get(this.getEconomicValue())  >
                        CardStatic.economicValue.get(cardToCompare.getEconomicValue()) );
                break;

            case "The Petrologist":
                testIs = (CardStatic.crustalAbundance.get(this.getEconomicValue())  >
                        CardStatic.crustalAbundance.get(cardToCompare.getEconomicValue()) );
                break;

            case "The Gemmologist":
                testIs = (hardness[hardness.length-1]  >
                        cardToCompare.hardness[cardToCompare.hardness.length-1] );
                break;

            case "The Mineralogist":
                testIs = (CardStatic.cleavage.get(this.getEconomicValue())  >
                        CardStatic.cleavage.get(cardToCompare.getEconomicValue()) );
                break;

            case "The Geophysicist" :
                testIs = (specificGravity[specificGravity.length-1]  >
                        cardToCompare.specificGravity[cardToCompare.specificGravity.length-1] );
                break;

            default:
                System.out.println("ERROR - Could not find trump when comparing cards");
                break;

        }
        return testIs;
    }

    //endregion


    //TODO: this code smells, refactor for MVC
    @Override
    public String getDetails() {
        String string = String.format(" %-11s | %-11s | %-20s | %-12s | %-10s |",
                Arrays.toString( getHardness()),
                Arrays.toString(getSpecificGravity()),
                getCleavage(),
                getCrustalAbundance(),
                getEconomicValue())
        ;

        return(super.getDetails() + string);

    }

/*** ways to compare two or more cards?? **/
    //should this be overrides tha

    /*** Errors  ***/
}