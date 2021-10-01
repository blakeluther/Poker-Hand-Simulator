package proj4;

import java.util.List;

public class Evaluator {

    int MAX_CARD_HAND_INDEX = 4; // Sets the highest index for a full hand of cards

    /**
     * Checks a hand to identify if it is a flush
     * Checks the suit of each card, and if one doesn't match it will return false,
     * else, return true
     * @param hand the hand to check
     * @return boolean, true if flush detected
     */
    private boolean isFlush(PokerHand hand){
        int initialSuit = hand.getIthCard(0).getSuit();
        for (int cardIndex = 1; cardIndex <= MAX_CARD_HAND_INDEX; cardIndex++){
            if (hand.getIthCard(cardIndex).getSuit() != (initialSuit)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks a hand to see if it contains a two pair
     * Runs the hand through getPairValues and if it contains 2 values, it will return true
     * @param hand the hand to check
     * @return boolean, true if two pair found
     */
    private boolean isPairs(PokerHand hand) {
        List<Integer> pairValues = Format.getPairValues(hand);
        return pairValues.size() == 2;
    }

    /**
     * Checks ta hand to see if it contains a pair
     * Runs hand through getPairValues and if it contains 1 value, it will return true
     * @param hand the hand to check
     * @return boolean, true if pair found
     */
    private boolean isPair(PokerHand hand) {
        List<Integer> pairValues = Format.getPairValues(hand);
        return pairValues.size() == 1;
    }

    /**
     * Checks hand for each type of hand (flush, two pair, pair and high card),
     * Will return a integer depending on what the hand contains
     * Higher integers = stronger hand
     * @param hand the hand to check
     * @return int corresponding to type of hand found
     */
    public int rankHand(PokerHand hand){
        if (isFlush(hand)) {
            return 4;
        } else if(isPairs(hand)){
            return 3;
        } else if (isPair(hand)){
            return 2;
        } else {
            return 1;
        }
    }

    /**
     * Compares two flushes of HighCard hands against each other.
     * Will create two lists of integers that each hand contains, in reverse order
     * Iterate through those integers, and if values don't match each other, then integers will be compared,
     * and winner declared.
     * NOTE: If all values are the same, it will return a 0 signifying a Tie
     * @param hand1 first hand to compare
     * @param hand2 second hand to compare
     * @return int 1,-1 or 0 to signify winner. (1= hand1, -1 hand2, 0 tie)
     */
    public int compareFlushOrHighCard(PokerHand hand1, PokerHand hand2){
        List<Integer> valuesOfCardsHand1 = Format.makeValueList(hand1);
        List<Integer> valuesOfCardsHand2 = Format.makeValueList(hand2);
        for (int cardIndex = 0; cardIndex <= MAX_CARD_HAND_INDEX; cardIndex++){
            int hand1Value = valuesOfCardsHand1.get(cardIndex);
            int hand2Value = valuesOfCardsHand2.get(cardIndex);
            if (hand1Value != hand2Value){
                return Integer.compare(hand1Value, hand2Value);
            }
        }
        return 0;
    }

    /**
     * Compares two pairs or two pair hands against each other.
     * Will create two list of integers that are pairs in each hand, and iterate through those integers,
     * and determine a winner. (1 or -1)
     * If all pair values are equal, then it will create a list of values are not pairs in the hand for each hand,
     * and iterate through those numbers, and determine winner (1 or -1)
     * If all singular values are equal to, then it will return a tie (0).
     * @param hand1 first hand to compare
     * @param hand2 second hand to compare
     * @return int 1, -1 or 0 to signify winner. (1 hand1, -1 hand2, 0 tie)
     */
    public int comparePairs(PokerHand hand1, PokerHand hand2){
        List<Integer> hand1Pairs = Format.getPairValues(hand1);
        List<Integer> hand2Pairs = Format.getPairValues(hand2);

        for (int cardIndex = 0; cardIndex < hand1Pairs.size(); cardIndex++){
            if (!hand1Pairs.get(cardIndex).equals(hand2Pairs.get(cardIndex))){
                return Integer.compare(hand1Pairs.get(cardIndex), hand2Pairs.get(cardIndex));
            }
        }
        List<Integer> hand1Singles = Format.getSingleNumbers(hand1);
        List<Integer> hand2Singles = Format.getSingleNumbers(hand2);

        if (hand1Singles.size() < hand2Singles.size()){
            return 1;
        } else if (hand2Singles.size() < hand1Singles.size()){
            return -1;
        }

        for (int cardIndex = 0; cardIndex < hand1Singles.size(); cardIndex++){
            if (!hand1Singles.get(cardIndex).equals(hand2Singles.get(cardIndex))){
                return Integer.compare(hand1Singles.get(cardIndex), hand2Singles.get(cardIndex));
            }
        }
        return 0;
    }
}
