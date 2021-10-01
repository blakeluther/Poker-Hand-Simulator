package proj4;

import java.util.*;

public class StudPokerHand {

    private final CommunityCardSet cc;
    private final ArrayList<Card> cardList;

    /**
     * Initializes a StudPokerHand
     * Takes the list of community cards and the list that represents the player hand
     * @param cc - abbrev. for the CommunityCardSet
     * @param cardList Holds the hole cards
     */
    public StudPokerHand(CommunityCardSet cc, ArrayList<Card> cardList){
        this.cc = cc;
        this.cardList = new ArrayList<>(cardList);;
    }

    /**
     * Adds a Card object to the list that holds the hole cards
     * If the list already contains two cards, nothing will happen
     * @param c Card object we are adding to the player's hand
     */
    public void addCard(Card c){
        int MAX_STUD_SIZE = 2;
        if (cardList.size() < MAX_STUD_SIZE){
            cardList.add(c);
        }
    }

    /**
     * Returns the card at the given index; assuming index is valid in the Hand
     * @param cardIndex the location of the card you want to get
     * @return a Card object
     */
    public Card getIthCard(int cardIndex){
        if (cardIndex < 0 || cardIndex > (cardList.size() + cc.getCommunityCardSize())){
            return null;
        } else {
            return cardList.get(cardIndex);
        }
    }

    /**
     * Creates a string of the Cards inside the Community Hand
     * @return String of Cards in Community Hand
     */
    public String toString() {
        StringBuilder cardListString = new StringBuilder("");
        cardListString.append(getIthCard(0)).append(" ").append("|");
        for (int card = 1; card < cardList.size(); card++)
        {
            if (card == cardList.size() - 1)
            {
                cardListString.append(" ").append(getIthCard(card));
            } else {
                cardListString.append(" ").append(getIthCard(card)).append(" ").append("|");
            }
        }
        return cardListString.toString();
    }

    /**
     * Determines how this hand compares to another hand, using the
     * community card set to determine the best 5-card hand it can
     * make. Returns positive, negative, or zero depending on the comparison.
     *
     * @param other The hand to compare this hand to
     * @return a negative number if this is worth LESS than other, zero
     * if they are worth the SAME, and a positive number if this is worth
     * MORE than other
     */
    public int compareTo(StudPokerHand other){
        PokerHand firstPlayerBestHand = getBestFiveCardHand();
        PokerHand secondPlayerBestHand = other.getBestFiveCardHand();
        return firstPlayerBestHand.compareTo(secondPlayerBestHand);
    }

    /**
     * Iterates through the seven cards, selecting two integers between 0 and 7
     * These integers represent the cards we will not be included in that particular hand
     * It selects two Cards, creates a new PokerHand,
     * then adds the Cards and adds that PokerHand to the ArrayList of PokerHands.
     * Will end up with 21 distinct Hands, as desired.
     * @return ArrayList containing all 21 different hands from the seven cards
     */
    private ArrayList<PokerHand> getAllFiveCardHands(){
        ArrayList<PokerHand> allHands = new ArrayList<>();
        for (int firstCard = 0; firstCard < 7; firstCard++){
            for (int secondCard = firstCard + 1; secondCard < 7; secondCard++){
                PokerHand handToAdd = new PokerHand(new ArrayList<Card>());
                for (int i = 0; i < 7; i++){
                    if (i != firstCard && i != secondCard){
                        if (i == 0 || i == 1){
                            handToAdd.addCard(getIthCard(i));
                        } else {
                            handToAdd.addCard(cc.getIthCard(i - 2));
                        }
                    }
                }
                allHands.add(handToAdd);
            }
        }
        return allHands;
    }

    /**
     * Iterates through the list of PokerHands as given by getAllFiveCardHands.
     * Takes the first PokerHand in the list and uses it as a base case for the best hand
     * Then goes through the rest of the list and compares each one to the best one of the previous hands.
     * If one hand beats the current best hand, the new hand takes the place of the the old best hand
     * After checking each one, it will return the best PokerHand of the list.
     * @return PokerHand - that is the best hand possible from the seven cards the player has
     */
    private PokerHand getBestFiveCardHand()
    {
        ArrayList<PokerHand> hands = getAllFiveCardHands();
        PokerHand bestSoFar = hands.get(0);
        for (int i = 1; i < hands.size(); i++) {
            if (hands.get(i).compareTo(bestSoFar) > 0) {
                bestSoFar = hands.get(i);
            }
        }
        return bestSoFar;
    }
}
