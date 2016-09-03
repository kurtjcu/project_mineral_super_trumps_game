package cardsPackage;

/**
 * Created by kurt on 16/08/2016.
 */
public class mineralCard extends BaseCard {



    private String chemistry;            // chemistry of mineral
    private String classification;        // classification of mineral??
    private String crystalSystem;        //enum? is this used??
    private String[] occurrence;            // need to contain types of rock it can be found in?? (Dictionary??)
    private Double[] hardness;            // can this be an int??
    private Double specificGravity;        // how heavy is it?
    private String cleavage;            // need to separate this into its components...
    private String crustalAbundance;    //change to enum??
    private String economicValue;        //change to enum??



    /*** Constructors ***/
    //
    public mineralCard(String fileName, String imageName, String cardType, String title,
                       String chemistry, String classification, String crystalSystem,
                       String[] occurrence, Double[] hardness, Double specificGravity,
                       String cleavage, String crystalAbundance, String economicValue) {

        super(fileName, imageName, cardType, title);

        this.fileName = fileName;
        this.imageName = imageName;
        this.title = title;
        this.chemistry = chemistry;
        this.classification = classification;
        this.crystalSystem = crystalSystem;
        this.occurrence = occurrence;           //TODO: need to get possible values for this and run a check for each value...
        this.hardness = hardness;
        this.specificGravity = specificGravity;



        if (cardStatic.dCleavage.get(cleavage) != null) {
            this.cleavage = cleavage;
        } else {
            errorLoadingCard("Cleavage not supported " + cleavage);
        }

        if (cardStatic.dCrustalAbundance.get(crystalAbundance) != null) {
            this.crustalAbundance = crystalAbundance;
        } else {
            errorLoadingCard("crystalAbundance not supported " + crystalAbundance);
        }

        if (cardStatic.dEconomicValue.get(economicValue) != null) {
            this.economicValue = economicValue;
        } else {
            errorLoadingCard("economicValue not supported " + economicValue);
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

    public Double getSpecificGravity() {
        return specificGravity;
    }

    public String getCleavage() {
        return cleavage;
    }

    public String getCrystalAbundance() {
        return crustalAbundance;
    }

    public String getEconomicValue() {
        return economicValue;
    }
    //endregion

/*** ways to compare two or more cards?? **/
    //should this be overrides tha

    /*** Errors ***/


}