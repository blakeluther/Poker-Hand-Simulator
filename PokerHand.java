package proj4;

import java.util.ArrayList;
import java.util.List;

public class PokerHand {

    private final List<Card> cardList; //creates a list that holds the contents of a PokerHand
    private final int TIE_HANDS = 0;
    private final int FLUSH_RANK = 4;
    private final int TWO_PAIR_RANK = 3;
    private final int ONE_PAIR_RANK = 2;

    /**
     * Initializes a PokerHand by taking in a ArrayList
     * which is then copied to the PokerHand object itself,
     * and stored as this.cardList
     * @param cardList The List that is holding the Cards for a player
     */
    public PokerHand(ArrayList<Card> cardList){
        this.cardList = new ArrayList<>(cardList);
    }

    /**
     * Adds a Card object to the player's PokerHand object,
     * If size of player's deck is greater than 5,
     * it will not add the card
     * @param c Card object we are adding to said player's hand
     */
    public void addCard(Card c){
        int MAX_HAND_SIZE = 5;
        if (this.cardList.size() < MAX_HAND_SIZE){
            cardList.add(c);
        }
    }

    /**
     * Takes in a integer, and finds the Card object at that index in the PokerHand object
     * if index is less than 0 or greater than highest index of the PokerHand object, will return null.
     * Else, will return the card at the index.
     * @param cardIndex integer at which the user wants the Card returned from
     * @return the Card object at inputted integer
     */
    public Card getIthCard(int cardIndex){
        int MAX_HAND_INDEX = 4;
        if (cardIndex < 0 || cardIndex > MAX_HAND_INDEX){
            return null;
        } else {
            return cardList.get(cardIndex);
        }
    }

    /**
     * Creates a string of the Cards inside a player's hand
     * @return String of Cards in PokerHand
     */
    public String toString() {
        StringBuilder cardString = new StringBuilder();
        for (Card card: cardList)
        {
            cardString.append(card);
        }
        return cardString.toString();
    }

    /**
     *  Determines how this hand compares to another hand, returns
     *  positive, negative, or zero depending on the comparison.
     *
     *  @param other The hand to compare this hand to
     *  @return a negative number if this is worth LESS than other, zero
     *  if they are worth the SAME, and a positive number if this is worth
     *  MORE than other
     */
    public int compareTo(PokerHand other){
        Evaluator evaluator = new Evaluator();

        int initialRankingHand1 = evaluator.rankHand(this);
        int initialRankingHand2 = evaluator.rankHand(other);

        int initialWinner = Integer.compare(initialRankingHand1, initialRankingHand2);

        if (initialWinner == TIE_HANDS){
            if (initialRankingHand1 == FLUSH_RANK && initialRankingHand2 == FLUSH_RANK){
                return evaluator.compareFlushOrHighCard(this, other);
            }
            else if (initialRankingHand1 == TWO_PAIR_RANK && initialRankingHand2 == TWO_PAIR_RANK){
                return evaluator.comparePairs(this, other);
            }
            else if (initialRankingHand1 == ONE_PAIR_RANK && initialRankingHand2 == ONE_PAIR_RANK){
                return evaluator.comparePairs(this, other);
            }
            else {
                return evaluator.compareFlushOrHighCard(this, other);
            }
        }
        return initialWinner;
    }
}
