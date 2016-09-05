import cardsPackage.BaseCard;
import java.util.*;

/**
 * Created by kurt on 26/08/2016.
 */
public class FisherYatesShuffle
{

    static void FisherYatesShuffle(int[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }

    //TODO: store cardstack as array to do the shuffel and return a cardstack???

    static Stack<BaseCard> FisherYatesShuffle(Stack<BaseCard> cardStack) {
        int n = cardStack.size();
        for (int i = 0; i < cardStack.size(); i++) {
            // Get a random index of the array past i.
            int random = i + (int) (Math.random() * (n - i));
            // Swap the random element with the present element.
            BaseCard randomElement = cardStack[random];
            cardStack[random] = cardStack[i];
            cardStack[i] = randomElement;
        }
        return cardStack;
    }

    public static void main(String[] args) {

        int[] values = { 100, 200, 10, 20, 30, 1, 2, 3 };
        // Shuffle integer array.
        FisherYatesShuffle(values);

        // Display elements in array.
        for (int value : values) {
            System.out.println(value);
        }
    }
}

