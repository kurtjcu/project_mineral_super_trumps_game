/**
 * Created by kurt on 16/08/2016.
 */
public class Card {

    private String fileName;			// name of image file
    private String imageName;			// name of image
    private CardType cardType;			// normal or trump?? (dont forget there is also an instructions type)
    private String title;				// name of card
    private String chemistry;			// chemistry of mineral
    private String classification;		// classification of mineral??
    private String crystalSystem;		//enum?
    private String occurrence;			// need to contain types of rock it cn be found in?? (array of strings??)
    private Double hardness;			// can this be an int??
    private Double specificGravity;		// how heavy is it?
    private String cleavage;			// need to seperate this into its components...
    private String crustalAbundance;	//change to enum??
	private String economicValue;		//change to enum??


	public enum CardType {
		PLAY, TRUMP, RULE;
	}

	public enum CrystalSystem {

	}

	public enum Cleavige {
		NONE(0), POOR(1), POOR1(2), POOR2(3), GOOD1(4), GOOD2(5), GOOD3(6), PERFECT1(7), PERFECT2(8), PERFECT3(9), PERFECT4(10), PERFECT5(11);
		private int value;
		private Cleavige(int value){
			this.value = value;
		}
	}
	public enum CrustalAbundance {
		ULTRATRACE(0), TRACE(1), LOW(2), MODERATE(3), HIGH(4), VERYHIGH(5);

		private int value;
		private CrustalAbundance(int value){
			this.value = value;
		}

	}

	/*** Constructors ***/

	/*** Setters  ***/

	/*** Getters ***/

	/*** ways to compair two or more cards?? **/
	//should this be overrides that depend on the trump??
}
