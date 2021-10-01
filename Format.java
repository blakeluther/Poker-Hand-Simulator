package proj4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Format {

    /**
     * Creates a list of Integers that contain the values of the Cards in a PokerHand,
     * Will reverse order to allow for easier comparisons later on
     * @param hand PokerHand that we want to get the values from
     * @return A list containing the values of all cards in PokerHand, reversed order
     */
    public static List<Integer> makeValueList(PokerHand hand){
        List<Integer> valuesOfCards = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            valuesOfCards.add(hand.getIthCard(i).getRank());
        }
        valuesOfCards.sort(Collections.reverseOrder());
        return valuesOfCards;
    }

    /**
     * Creates a list of Integers of values that only occur once in a PokerHand,
     * Reverse the list for easier comparisons later
     * @param hand PokerHand that we want to get the values from
     * @return A list containing the values that appear once
     */
    public static List<Integer> getSingleNumbers(PokerHand hand) {
        int singleValue = 1;
        List<Integer> cardValues = makeValueList(hand);
        List<Integer> singles = new ArrayList<>();
        for (int rank : cardValues){
            if (Collections.frequency(cardValues, rank) == singleValue){
                singles.add(rank);
            }
        }
        singles.sort(Collections.reverseOrder());
        return singles;
    }

    /**
     * Creates a list of Integers of values that only occur twice of more in a PokerHand
     * Reverses the list for easier comparisons later on
     * @param hand PokerHand that we want to get the values from
     * @return  A list containing the values that appear twice or more
     */
    public static List<Integer> getPairValues(PokerHand hand) {

        int isPair = 2;
        int isThreeOfAKind = 3;
        int isFourOfAKind = 4;

        List<Integer> cardValues = makeValueList(hand);
        cardValues.sort(Collections.reverseOrder());
        List<Integer> pairs = new ArrayList<>();
        for (int rank : cardValues){
            int counter = Collections.frequency(cardValues, rank);
            if (!pairs.contains(rank)){
                if (counter == isPair || counter == isThreeOfAKind){
                    pairs.add(rank);
                }
                else if (counter == isFourOfAKind){
                    pairs.add(rank);
                    pairs.add(rank);
                }
            }
        }
        return pairs;
    }

}
